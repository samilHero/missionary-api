package com.samill.missionary_backend.missionary.board.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(
        access = AccessLevel.PROTECTED
)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE missionary_board SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "missionary_board")
public class MissionaryBoard extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Enumerated(value = EnumType.STRING)
    private MissionaryBoardType type;

    private String title;

    private String content;

    @Builder.Default
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissionaryBoardFile> files = new ArrayList<>();

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "missionary_id")
    private Missionary missionary;

    @Transient
    private MissionaryBoardWriter writer;

    private OffsetDateTime deletedAt;

    void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    void setWriter(MissionaryBoardWriter writer) {
        this.writer = writer;
    }

}
