package com.bookshop.service.Impl;

import com.bookshop.DTO.Book;
import com.bookshop.DTO.Comment;
import com.bookshop.converter.BookConverter;
import com.bookshop.converter.CommentConverter;
import com.bookshop.entity.BookEntity;
import com.bookshop.entity.CommentEntity;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.CommentRepository;
import com.bookshop.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private BookConverter bookConverter;

    @Autowired
    private BookRepository bookRepository;
    @Override
    public Comment getCommentById(int cid) {
        Optional<CommentEntity> entity = commentRepository.findById(cid);
        if(entity.isPresent()) return commentConverter.toDto(entity.get());
        return new Comment();
    }

    @Override
    public List<Comment> getCommentsOfBook(int bid) {
        BookEntity entity = bookRepository.findById(bid).get();
        List<Comment> comments = entity.getCommentList().stream().map(commentConverter::toDto).toList();
        return comments;
    }

    @Override
    public Comment addComment(Comment comment) {
        System.out.println(comment.getBook().getId());
        CommentEntity entity = commentConverter.toEntity(comment);
        System.out.println(entity.getBook().getId());
        Comment response = commentConverter.toDto(commentRepository.save(entity));
        return response;
    }

    @Override
    public Comment updateComment(Comment comment) {
        CommentEntity entity = commentConverter.toEntity(comment);
        Comment response = commentConverter.toDto(commentRepository.save(entity));
        return response;
    }

    @Override
    public void deleteComment(int cid) {
        commentRepository.deleteById(cid);
    }
}
