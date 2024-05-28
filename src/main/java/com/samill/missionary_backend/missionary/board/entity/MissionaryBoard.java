package com.samill.missionary_backend.missionary.board.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.missionary.board.enums.MissionaryBoardType;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@NoArgsConstructor(
    access = AccessLevel.PROTECTED
)
@Builder
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
    private MissionaryBoardFiles files = new MissionaryBoardFiles();

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "missionary_id")
    private Missionary missionary;

    @Transient
    private MissionaryBoardWriter writer;

    private OffsetDateTime deletedAt;

    public void changeTitle(@NonNull String title) {
        this.title = title;
    }

    public void changeContent(@NonNull String content) {
        this.content = content;
    }

    public void changeFiles(
        @NonNull
        List<String> removeTargets,
        @NonNull
        MissionaryBoardFiles newFiles
    ) {
        files.changeFiles(removeTargets, newFiles);
    }

    public void linkMissionary(Missionary missionary) {
        this.missionary = missionary;
    }

    public String getMissionaryId() {
        return missionary.getId();
    }

    public int getFilesCount() {
        return files.getFiles().size();
    }
}
