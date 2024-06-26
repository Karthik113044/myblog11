package com.myblog.service.impl;

import com.myblog.entity.Comment;
import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;
//import com.myblog.payload.UpdateComment;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.PostRepository;
import com.myblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    private ModelMapper modelMapper;



    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceAccessException("Post Not Found with id:"+postId)
        );

        Comment comment = new Comment();

        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());
        comment.setPost(post);


        Comment saveComment = commentRepository.save(comment);

        CommentDto dto = new CommentDto();

        dto.setId(saveComment.getId());
        dto.setEmail(saveComment.getEmail());
        dto.setText(saveComment.getText());
        return dto;
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long id, long postId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment not found for id:" + id)
        );

        Comment c = mapToEntity(commentDto);

        c.setId(comment.getId());
        c.setPost(comment.getPost());
        Comment savedComment = commentRepository.save(c);

        return mapToDto(savedComment);
    }

    CommentDto mapToDto (Comment comment){
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }
    Comment mapToEntity (CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}
