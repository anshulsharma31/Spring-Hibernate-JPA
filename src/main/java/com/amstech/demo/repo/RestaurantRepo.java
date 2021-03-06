/*
* Copyright 2017 AMSTECH INCORPORATION PRIVATE LIMITED.
* (www.amstechinc.com)
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

package com.amstech.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amstech.demo.entity.Restaurant;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {

	@Modifying
	@Transactional
	@Query("update Restaurant e set e.isDeleted = 1 where e.id =:id")
	int markDeleted(@Param("id") Integer id);

	@Query("select e from Restaurant e where e.isDeleted = 0 and e.id =:id")
	Restaurant findOne(@Param("id") Integer id);

	@Query("select e from Restaurant e where e.isDeleted = 0 and e.city.id =:cId")
	List<Restaurant> findByCityId(@Param("cId") Integer cId);

}
