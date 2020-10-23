package com.company.coronavirusReliableInfos_API;

import com.company.coronavirusReliableInfos_API.controller.ArticleController;
import org.apache.log4j.Logger;

import java.time.format.DateTimeFormatter;

public class LoggerConfiguration {
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
}
