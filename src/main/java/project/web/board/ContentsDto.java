package project.web.board;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="contents")
public class ContentsDto {
    @Id
    private long no;
    private String contents;
}
