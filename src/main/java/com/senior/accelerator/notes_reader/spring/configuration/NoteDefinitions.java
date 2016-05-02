package com.senior.accelerator.notes_reader.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.senior.accelerator.notes_reader.dao.DesignPatternNote;
import com.senior.accelerator.notes_reader.dao.NoteJsonFileData;
import com.senior.accelerator.notes_reader.dao.RegularNote;

/**
 *{@NoteJsonFileData} beans are created here.
 * @author Peter Takacs
 */
@Configuration
public class NoteDefinitions {

    @Bean
    public NoteJsonFileData regularNote0(){
        return new NoteJsonFileData("Java_7_0.json", RegularNote.class);
    }

    @Bean
    public NoteJsonFileData regularNote1(){
        return new NoteJsonFileData("Java_7_1.json", RegularNote.class);
    }

    @Bean
    public NoteJsonFileData regularNote2(){
        return new NoteJsonFileData("Java_7_2.json", RegularNote.class);
    }

    @Bean
    public NoteJsonFileData designPatternNote0(){
        return new NoteJsonFileData("Design_Pattern_0.json", DesignPatternNote.class);
    }

    @Bean
    public NoteJsonFileData designPatternNote1(){
        return new NoteJsonFileData("Design_Pattern_1.json", DesignPatternNote.class);
    }

    @Bean
    public NoteJsonFileData designPatternNote2(){
        return new NoteJsonFileData("Design_Pattern_2.json", DesignPatternNote.class);
    }
}
