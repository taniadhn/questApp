package com.dehghan.questapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    Long id;
   /// Long userId; --> Bu alan bir User Object temsil ediyor.
    /**Post Object çaktığımde ilgili User bana getirmene gerek yoktur*/
    @ManyToOne(fetch = FetchType.LAZY)  /**Bir sürü post tek bir User bağlı olabilir*/
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) /**Bir User silindiğinde tüm postları silisin*/
    @JsonIgnore
    User user;
    String title;
    @Lob
    @Column(columnDefinition = "text") /** Hibernate bunu String olarak algılaması için yaptık bunu yoksa
     Varchar olarak algılıyor
     */
    String text;
}
