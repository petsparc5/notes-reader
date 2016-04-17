package com.senior.accelerator.notes_reader.reader;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * Factory that creates lists with file names.
 * @author Peter_Takacs
 */
@Component
public class FileNameFactory {

    private List<String> fileNamesForJava7;
    private List<String> fileNamesForDesignPatterns;

    @PostConstruct
    public void init() {
        fileNamesForJava7 = new ArrayList<>();
        fileNamesForJava7.add("Java_7_0.json");
        fileNamesForJava7.add("Java_7_1.json");
        fileNamesForJava7.add("Java_7_2.json");
        fileNamesForDesignPatterns = new ArrayList<>();
        fileNamesForDesignPatterns.add("Design_Pattern_0.json");
        fileNamesForDesignPatterns.add("Design_Pattern_1.json");
        fileNamesForDesignPatterns.add("Design_Pattern_2.json");
    }

    /**
     * Creates a list with all the filenames for a specific subject.
     * @param subject - filtering is applied on by this value on filenames.
     * @return list of filenames for a given subject.
     */
    public List<String> createFileNamesBySubject(String subject) {
        List<String> fileNamesFound = new ArrayList<>();
        switch (subject) {
        case "Java 7":
            fileNamesFound = fileNamesForJava7;
            break;
        case "Design Patterns":
            fileNamesFound = fileNamesForDesignPatterns;
            break;
        default:
            break;
        }
        return fileNamesFound;
    }

    /**
     * Creates a list with all the filenames.
     * @return list of all the filenames.
     */
    public List<String> createAllFileNames() {
        List<String> fileNamesFound = new ArrayList<>();
        fileNamesFound.addAll(fileNamesForJava7);
        fileNamesFound.addAll(fileNamesForDesignPatterns);
        return fileNamesFound;
    }
}
