package project.web.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity(name="board")
@SequenceGenerator(
        name="SEQ_BOARD_GEN",
        sequenceName = "SEQ_BOARD",
        initialValue = 1,
        allocationSize = 1
        )
public class BoardDto {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_BOARD_GEN"
            )
    private long no;
    private String title;
    private String writer;
    private String dates;
    private int views;
    private int likes;
}
