package com.project.hunter.domain.dto.pagination;

import java.util.List;

public class PageResult<T> {
    private MetaData meta;
    private List<T> results;

    public PageResult(MetaData meta, List<T> results) {
        this.meta = meta;
        this.results = results;
    }

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }


}
