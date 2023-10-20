package com.gathergrid.gathergridfeatures.repository.interfaces;

import com.gathergrid.gathergridfeatures.domain.Comment;

import java.util.List;

public interface CommentRepositry {
    public Comment save(Comment c);
    public Comment findById(Long id);
    public Comment update(Comment c);
    public void delete(Long comment_id);
    public List<Comment> show(Long event_id);
}
