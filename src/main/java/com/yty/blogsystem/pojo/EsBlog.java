package com.yty.blogsystem.pojo;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


@Document(indexName = "blog", type = "java")
public class EsBlog implements Serializable {
    private String id;
    private String name;
    private String title;
    private String summary;
    private String content;

    protected EsBlog(){

    }

    protected EsBlog(String title,String summary,String content){
        this.title=title;
        this.content=content;
        this.summary=summary;
    }

    @Override
    public String toString(){
        return String.format("Esblog[id='%s',title='%s',summary='%s',content='%s']",id,title,summary,content);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
