package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;
import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String articleTitle;
    private final String articleText;
    private final UUID id;

    public Article(String name, String articleText){
        this.articleTitle = name;
        this.articleText = articleText;
        this.id = UUID.randomUUID();
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleText(){
        return articleText;
    }

    @Override
    public String toString() {
        return this.getArticleTitle() + "\r\n" + this.getArticleText();
    }

    @JsonIgnore
    public String getSearchTerm() {
        return getArticleTitle();
    }

    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    public UUID getId(){
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Article article = (Article) obj;
        return Objects.equals(articleTitle, article.articleTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleTitle);
    }
}
