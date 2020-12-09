package project.web.backend;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("board")
public class BoardController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BoardController.class);
    @Autowired
    private BoardLogic boardLogic;

    @ResponseBody
    @GetMapping("current_time")
    public Object current_time(@RequestParam Map<String, Object> pMap) {
        logger.info("BoardController - current_time");
        try {
            return boardLogic.current_time(pMap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
