package com.db.resource.controller.urlreports;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlReportController {


	/*@GetMapping("/widgetparam/widgetid/{widgetId}")
	public ListWidgetParameters getParameterValues(@PathVariable("widgetId") String widgetId) {
		return widgetParamsService.findById(widgetId);
	}

	@GetMapping("/widgetquery/widgetid/{widgetId}")
	public ListWidgetQueries getWidgetQueriesById(@PathVariable("widgetId") String widgetId) {
		return queryService.getListWidgetQueries(widgetId);
	}

	@GetMapping("/widgetquery/widgetname/{widgetName}")
	public ListWidgetQueries getWidgetQueriesByName(@PathVariable("widgetName") String widgetName) {
		Widget widget = widgetService.findByWidgetName(widgetName);
		return queryService.getListWidgetQueries(widget.getId().toString());
	}*/
	
	@GetMapping("/test/{test}")
	public String getParametersByReportName(@PathVariable("test") String test) {
		return test;
	}

	/*
		@GetMapping("/test/{url}/{tempFile}")
		public @ResponseBody String test(@PathVariable("url") String url, @PathVariable("tempFile") String file) {
			String result = "";
			try {
				Path path = Paths.get(this.getClass().getClassLoader().getParent().getResource("").getPath());
				Runtime rt = Runtime.getRuntime();
				String[] commands;
				commands = new String[] { "phantomjs", "rasterize.js", "\"" + url + "\"", file };
				Process ps;
				ps = rt.exec(commands, null, path.toFile());
				ps.waitFor();
				result = ps.exitValue() + "";
			} catch (IOException e) {
				result = e.getMessage();
				result += e.getLocalizedMessage();
				e.printStackTrace();
			} catch (InterruptedException e) {
				result = e.getMessage();
				result += e.getLocalizedMessage();
				e.printStackTrace();
			}
			result += UrlReportController.class.getClassLoader().getResource("").getPath() + "_";
			return result;
		}
	*/

}
