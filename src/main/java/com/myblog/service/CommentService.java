package com.myblog.service;

import com.myblog.payload.CommentDto;

public interface CommentService {

   CommentDto createComment(CommentDto commentDto, long postId);

   void deleteComment(long id);

   CommentDto updateComment(long id, long postId,CommentDto commentDto);
}
