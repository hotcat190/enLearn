import javax.swing.*;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement implements SearchEngine {
    private final Dictionary dictionary;
    //    private final DictionaryCommandline dictionaryCommandline;
    private final Scanner programScanner;

    public Dictionary getDictionary() {
        return dictionary;
    }

    public DictionaryManagement() {
        this.dictionary = new Dictionary();
//        this.dictionaryCommandline = new DictionaryCommandline(dictionary);
        this.programScanner = new Scanner(System.in);
        System.out.println("Loading!");
        initateData();
    }

    private void initateData() {
        try {
            List<Word> wordList = dictionary.getListOfWords();
            Scanner sc = new Scanner(new File("D:\\enLearn\\resource\\dictionary.txt"));
            int count = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\t");
                try {
                    String word = parts[0];
                    String meaning = parts[1];
                    word = word.trim();
                    meaning = meaning.trim();
                    wordList.add(new Word(word, meaning));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.err.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void shutDownProcess() {
        try {
            List<Word> wordList = dictionary.getListOfWords();
            Collections.sort(wordList);
            BufferedWriter bf = new BufferedWriter(new FileWriter("D:\\enLearn\\resource\\dictionary.txt"));
            for (Word word : wordList) {
                StringBuilder line = new StringBuilder(word.getWord_target());
                line.append("\t");
                line.append(word.getWord_explain());
                line.append("\n");
                bf.write(line.toString());
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteWord() {
        System.out.print("Insert the word you want to delete: ");
        String word_target = programScanner.nextLine().trim();
        Word dst = binSearch(new Word(word_target, ""));
        if (dst == null) {
            System.out.println("The word you want to delete doesn't exist in this dictionary");
        } else {
            dictionary.remove(dst);
        }
    }

    public void dictionaryExportToFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                List<Word> wordList = dictionary.getListOfWords();
                BufferedWriter bf = new BufferedWriter(new FileWriter(file));
                for (Word word : wordList) {
                    StringBuilder line = new StringBuilder(word.getWord_target());
                    line.append("\t");
                    line.append(word.getWord_explain());
                    line.append("\n");
                    bf.write(line.toString());
                }
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isEmpty() {
        return dictionary.getListOfWords().isEmpty();
    }

    public void updateWord() {
        System.out.print("Insert the word you want to update: ");
        String word_target = programScanner.nextLine().trim();
        System.out.print("Insert the meaning you want to update: ");
        String word_explain = programScanner.nextLine().trim();
        Word word = new Word(word_target, "-" + word_explain);
        int index = binSearchIndex(word);
        Word dst = dictionary.getWordfromIndex(index);
        if (dst.compareTo(word) != 0) {
            System.out.println("The word you chose doesn't exist in this dictionary");
        } else {
            dictionary.update(index, word);
        }
    }

    private int binSearchIndex(Word word) {
        List<Word> wordList = dictionary.getListOfWords();
        return SearchEngine.binSearchIndex(wordList, 0, wordList.size() - 1, word);
    }

    public void addWord() {

        System.out.print("Insert the word you want to add : ");
        String word_target = programScanner.nextLine().trim();
        System.out.print("Insert the meaning of the word : ");
        String word_explain = programScanner.nextLine().trim();

        Word word = new Word(word_target, "-" + word_explain);
        int index = binSearchIndex(word);

        if (!isEmpty() && dictionary.getWordfromIndex(index).equals(word.getWord_target())) {
            System.out.println("The word you chose has already existed");
        } else {
            dictionary.add(index, word);
        }
    }

    private Word binSearch(Word word) {
        List<Word> wordList = dictionary.getListOfWords();
        return SearchEngine.binSearch(wordList, 0, wordList.size() - 1, word);
    }

    public Word dictionaryLookup() {
        System.out.print("Insert the word you want to lookup : ");
        String word = programScanner.nextLine();
        word = word.trim();
        Word result = binSearch(new Word(word, ""));
        if (result == null) {
            System.out.println("There's no such word in the dictionary");
            return null;
        } else {
            return result;
        }

    }

    public boolean insertFromFile(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            int count = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\t");
                try {
                    String word = parts[0];
                    String meaning = parts[1];
                    word = word.trim();
                    meaning = meaning.trim();
                    dictionary.add(new Word(word, meaning));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.err.println(line);
                }
            }
            dictionary.validateDictionary();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void insertFromFile() {
        try {
            Scanner sc = new Scanner(new File("D:\\enLearn\\resource\\dictionary.txt"));
            int count = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\t");
                try {
                    String word = parts[0];
                    String meaning = parts[1];
                    word = word.trim();
                    meaning = meaning.trim();
                    dictionary.add(new Word(word, meaning));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.err.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertFromCommandline() {


        System.out.print("Number of inserts: ");
        int numberOfInserts = Integer.parseInt(programScanner.nextLine());

        for (int i = 0; i < numberOfInserts; i++) {
            System.out.print("Insert word: ");
            String target = programScanner.nextLine().trim();

            System.out.print("Specify explanation: ");
            String explain = programScanner.nextLine().trim();

            Word word = new Word(target, explain);
            this.dictionary.add(word);
        }
    }

}
