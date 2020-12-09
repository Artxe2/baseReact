package project.web.backend;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.chart.ChartFormat;
import com.google.chart.GoogleChart;

@Controller
@RequestMapping("base")
public class BaseController {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseController.class);
	@Autowired
	private BaseLogic baseLogic;

	@ResponseBody
	@GetMapping("current_time")
	public Object current_time(@RequestParam Map<String, Object> pMap) {
		logger.info("BaseController - current_time");
		try {
			return baseLogic.current_time(pMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("file_upload")
	public Object file_upload(@RequestParam Map<String, Object> pMap, @RequestPart("i_file") MultipartFile[] files) {
		logger.info("BaseController - file_upload");
		try {
			return baseLogic.file_upload(pMap, files);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("file_download")
	public Object file_download() {
		logger.info("BaseController - file_upload");
		return "base/file_download";
	}


	@ResponseBody
	@GetMapping("geo_chart")
	public Object geo_chart() {
		logger.info("BaseController - geo_chart");
		try {
			return GoogleChart.toChartDataTable("COUNTRY", baseLogic.geo_chart(), null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@GetMapping("core_chart")
	public Object core_chart() {
		logger.info("BaseController - core_chart");
		try {
			return GoogleChart.toChartDataTable("ROWNAME", baseLogic.core_chart(), null, null);
			//			return GoogleChart.toSimpleTable("ROWNAME", baseLogic.core_chart());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@GetMapping("scatter_chart")
	public Object core_chart2() {
		logger.info("BaseController - scatter_chart");
		try {
			return GoogleChart.toChartDataTable(null, baseLogic.scatter_chart(), null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@GetMapping("core_chart2")
	public Object core_chart3() {
		logger.info("BaseController - core_chart2");
		try {
			return GoogleChart.toChartDataTable("ROWNAME", baseLogic.core_chart2(), null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@GetMapping("org_chart")
	public Object org_chart() {
		logger.info("BaseController - org_chart");
		try {
			return GoogleChart.toChartDataTable("ENAME", baseLogic.org_chart(), new String[] {"MGR", "JOB"},
					new ChartFormat(ChartFormat.ORG_CHART, null,
							new String[] {"<div style=\"color:red; font-style:italic\">", "#JOB", "</div>", "#ENAME"}));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@GetMapping("tree_map")
	public Object tree_map() {
		logger.info("BaseController - tree_map");
		try {
			return GoogleChart.toChartDataTable("ENAME", baseLogic.tree_map(), new String[] {"PARENT"}, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@GetMapping("table")
	public Object table() {
		logger.info("BaseController - table");
		try {
			return GoogleChart.toChartDataTable("ENAME", baseLogic.table(), null,
					new ChartFormat(ChartFormat.TABLE, "SAL", new String[] {"HAS_COMM"}));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@GetMapping("timeline")
	public Object timeline() {
		logger.info("BaseController - timeline");
		try {
			return GoogleChart.toChartDataTable("ENAME", baseLogic.timeline(), null,
					new ChartFormat(ChartFormat.TIMELINE, "SAL", new String[] {"HAS_COMM"}));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
