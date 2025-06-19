package com.example.outsourcing_project.comment.service;

import com.example.outsourcing_project.comment.dto.CommentRequestDto;
import com.example.outsourcing_project.comment.dto.CommentResponseDto;
import com.example.outsourcing_project.comment.dto.CommentUpdateRequestDto;
import com.example.outsourcing_project.comment.entity.Comment;
import com.example.outsourcing_project.comment.repository.CommentRepository;
import com.example.outsourcing_project.member.entity.Member;
import com.example.outsourcing_project.member.repository.MemberRepository;
import com.example.outsourcing_project.task.entity.Task;
import com.example.outsourcing_project.task.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;


//create comment
    @Transactional
    public CommentResponseDto createComment(Long taskId, CommentRequestDto dto) {
        //  Task 가져오기
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("해당 태스크를 찾을 수 없습니다."));

        //  사용자 정보 (임시로 userId = 1L)
        Member author = memberRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("작성자 정보를 찾을 수 없습니다."));

        // Comment 엔티티 생성
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setAuthor(author);
        comment.setTask(task);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setIsDeleted(false);

        // 저장
        Comment saved = commentRepository.save(comment);

        // ResponseDTO로 반환
        return new CommentResponseDto(
                saved.getId(),
                saved.getAuthor().getUsername(),
                saved.getContent(),
                saved.getCreatedAt()
        );
    }
    // get comment
    public List<CommentResponseDto> getCommentsByTaskId(Long taskId) {
        List<Comment> comments = commentRepository.findAllByTaskIdOrderByCreatedAtAsc(taskId);
        return comments.stream()
                .map(c -> new CommentResponseDto(
                        c.getId(),
                        c.getContent(),
                        c.getAuthor().getUsername(),
                        c.getCreatedAt()
                ))
                .toList();
    }
    // Edit comment
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));

        comment.setContent(requestDto.getContent());
        // createdAt은 그대로, updatedAt이 있다면 갱신해도 됨

        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor().getUsername(),
                comment.getCreatedAt()
        );

    }
    //Delete comment (hard delete)
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));

        commentRepository.delete(comment);
    }



}
