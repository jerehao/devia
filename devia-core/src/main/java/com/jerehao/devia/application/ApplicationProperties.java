/*
 * Copyright (c) 2018, jerehao.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jerehao.devia.application;

import com.jerehao.devia.core.resource.FileSystemResource;
import com.jerehao.devia.core.util.ClassUtils;
import com.jerehao.devia.core.util.FileUtils;
import com.jerehao.devia.core.util.ResourceUtils;
import com.jerehao.devia.core.util.WebUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author <a href="http://jerehao.com">jerehao</a>
 * @version 0.0.1 2018-01-18 19:36 jerehao
 */
public final class ApplicationProperties {


    private static final String APPLICATION_PROPERTIES_FILE_PATH = "/application.properties";

    private static final Properties applicationProperties = new Properties();

    private static boolean init = false;

    /**
     * read application properties
     */

    static {
        readProperties();
    }


    public static String getProperty(String key) {
        return applicationProperties.getProperty(key);
    }

    public static String getPropertyOrDefault(String key, String _default) {
        return applicationProperties.getProperty(key, _default);
    }

    public static void readProperties() {
        if(init)
            return;

        FileSystemResource resource = new FileSystemResource(WebUtils.getContextPath()
                + "/WEB-INF"
                + APPLICATION_PROPERTIES_FILE_PATH);

        if(resource.isExist()) {
            try {
                applicationProperties.load(resource.getInputStream());
                init = true;
                return;
            } catch (IOException ignored) {
            }
        }
        InputStream in;
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        in = classLoader.getResourceAsStream(APPLICATION_PROPERTIES_FILE_PATH);
        if(in != null) {
            try {
                applicationProperties.load(in);
                init = true;
                return;
            } catch (IOException ignored) {
            }
        }
        throw new RuntimeException("Read application.properties file failed," +
                " please make sure that the file is locate at classpath or /WEB-INF/");

    }

    private ApplicationProperties() {}

    public static class Keys {

        public static final String DATABASE_ENGINE = "database.engine";

        public static final String DATABASE_MODE = "database.mode";


        public static final String JDBC_DRIVER = "jdbc.driver";

        public static final String JDBC_USERNAME = "jdbc.username";

        public static final String JDBC_PASSWORD = "jdbc.password";

        public static final String JDBC_URL = "jdbc.url";

        public static final String JDBC_TRANSACTION_ISOLATION = "jdbc.translation.isolation";
    }
}
