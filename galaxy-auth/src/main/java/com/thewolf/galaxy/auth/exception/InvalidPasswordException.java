/**
 * Copyright 2017 thewolf
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
package com.thewolf.galaxy.auth.exception;

import com.thewolf.galaxy.common.web.exception.base.UnauthorizedException;

/**
 * Invalid password exception.
 */
public class InvalidPasswordException extends UnauthorizedException {

  /**
   * Numeric error code for the exception.
   */
  public static final int NUMERIC_ERROR_CODE = AuthCodeBase.NUMERIC_UNAUTHORIZED_RANGE_BASE + 2;

  /**
   * String error code for the exception.
   */
  public static final String ERROR_CODE = AuthCodeBase.UNAUTHORIZED_ERROR_BASE + "invalid_password";

  /**
   * Constructor.
   */
  public InvalidPasswordException() {
    super(NUMERIC_ERROR_CODE, ERROR_CODE, "Invalid password.");
  }
}
