package com.example.pictgram.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "comment")
@Data
public class Comment extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    /* プライマリーキーを指定*/
    @SequenceGenerator(name = "comment_id_seq")
    /* strategy,keyを生成*/
    /* 関連するテーブルのカラムタイプを SERIAL にする*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long topicId;

    @Column(nullable = false, length = 1000)
    private String description;
}
