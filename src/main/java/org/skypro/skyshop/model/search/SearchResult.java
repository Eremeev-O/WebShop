package org.skypro.skyshop.model.search;

public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }
    public static SearchResult fromSearchable(Searchable searchable){
        return new SearchResult(searchable.getId().toString(), searchable.getSearchTerm(), searchable.getContentType());
    }

    @Override
    public String toString() {
        return this.id + " : " + this.name + "  " + this.contentType;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getContentType(){
        return this.contentType;
    }

}
