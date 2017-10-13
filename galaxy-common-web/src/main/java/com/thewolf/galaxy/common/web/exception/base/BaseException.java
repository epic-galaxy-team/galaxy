/**
 * Copyright 2017 thewolf
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thewolf.galaxy.common.web.exception.base;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.helpers.MessageFormatter;

/**
 * Base class for galaxy exception system.
 */
@Getter
@Setter
public abstract class BaseException extends RuntimeException {

  protected String message;

  protected String messageTemplate;

  /**
   * constructor.
   *
   * @param pattern message pattern.
   * @param args    message args.
   */
  public BaseException(String pattern, Object... args) {
    super();
    setMessageTemplate(pattern);
    setMessage(MessageFormatter.arrayFormat(pattern, args).getMessage());
  }
}
