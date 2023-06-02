package com.bookshop.service;

import com.bookshop.DTO.Comment;


import java.util.List;

public interface ICommentService{
    Comment getCommentById(int cid);

    List<Comment> getCommentsOfBook(int bid);

    Comment addComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(int cid);
}
