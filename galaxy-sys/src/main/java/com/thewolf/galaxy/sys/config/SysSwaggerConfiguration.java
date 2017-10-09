/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.thewolf.galaxy.sys.config;

import com.thewolf.galaxy.common.web.swagger.SwaggerConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;

import static com.thewolf.galaxy.common.web.swagger.SwaggerConfiguration.newApiInfo;
import static com.thewolf.galaxy.common.web.swagger.SwaggerConfiguration.newDocket;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Auth swagger configuration.
 */
@ConditionalOnBean(SwaggerConfiguration.class)
public class SysSwaggerConfiguration {

  /**
   * Api info.
   *
   * @return api info.
   */
  @Bean(name = "sysApiInfo")
  ApiInfo authApiInfo() {
    return newApiInfo("Galaxy Sys API", "galaxy sys module", "1.0");
  }

  /**
   * Galaxy Sys api docket.
   *
   * @param apiInfo api info.
   * @return coupons docket.
   */
  @Bean
  public Docket authApi(@Qualifier("sysApiInfo") ApiInfo apiInfo) {
    return newDocket("sys-api", apiInfo, regex("/api/sys/.*"));
  }
}
