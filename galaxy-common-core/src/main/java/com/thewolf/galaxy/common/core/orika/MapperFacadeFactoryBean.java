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
package com.thewolf.galaxy.common.core.orika;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Orika mapper facade factory bean.
 */
@Slf4j
@SuppressWarnings({"unchecked", "unused"})
@Component
public class MapperFacadeFactoryBean extends AbstractFactoryBean<MapperFacade>
  implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  private MapperFactory getMapperFactory() {

    final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();

    for (Map.Entry<String, Converter> entry :
      this.applicationContext.getBeansOfType(Converter.class).entrySet()) {
      registerConverter(entry.getKey(), entry.getValue(), mapperFactory);
    }

    for (Map.Entry<String, Mapper> entry :
      this.applicationContext.getBeansOfType(Mapper.class).entrySet()) {
      registerMapper(entry.getKey(), entry.getValue(), mapperFactory);
    }

    for (Map.Entry<String, MapperConfigurer> entry :
      this.applicationContext.getBeansOfType(MapperConfigurer.class).entrySet()) {
      applyConfigurer(entry.getKey(), entry.getValue(), mapperFactory);
    }

    return mapperFactory;
  }

  /**
   * Register core converter.
   *
   * @param name      converter name
   * @param converter converter
   * @param factory   mapper factory
   * @param <S>       converter source type
   * @param <D>       converter destination type
   */
  private <S, D> void registerConverter(
    String name, Converter<S, D> converter, final MapperFactory factory) {
    log.debug("Registering converter [{}]: {}", name, converter);

    factory.getConverterFactory().registerConverter(converter);
  }

  /**
   * Register core mapper.
   *
   * @param name    mapper name
   * @param mapper  mapper
   * @param factory mapper factory
   * @param <A>     mapper A type
   * @param <B>     mapper B type
   */
  private <A, B> void registerMapper(
    String name, Mapper<A, B> mapper, final MapperFactory factory) {
    log.debug("Registering mapper [{}]: {}", name, mapper);

    factory.classMap(mapper.getAType(), mapper.getBType()).byDefault().customize(mapper).register();
  }

  /**
   * Apply core configurer.
   *
   * @param name       configurer name
   * @param configurer configurer
   * @param factory    mapper factory
   */
  private void applyConfigurer(String name, MapperConfigurer configurer, MapperFactory factory) {
    log.debug("Applying configurer [{}]: {}", name, configurer);

    configurer.configure(factory);
  }

  @Override
  public Class<?> getObjectType() {
    return MapperFacade.class;
  }

  @Override
  protected MapperFacade createInstance() throws Exception {
    return getMapperFactory().getMapperFacade();
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
