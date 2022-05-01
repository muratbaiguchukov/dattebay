package kg.itacademy.testproject.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "videos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoEntity extends BaseEntity {

    @Column(name = "video_name", nullable = false)
    String videoName;

    @Column(name = "video_url", nullable = false, unique = true)
    String videoUrl;

}
