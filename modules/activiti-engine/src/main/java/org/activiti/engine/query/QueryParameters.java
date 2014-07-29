/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.engine.query;

/**
 * Implementation classes can hold custom query parameters. 
 * Query parameters in implemented classes are not supported directly by
 * Query API, you will need to add appropriate SQL checks by modify the 
 * XML mapping files.
 * 
 * A QueryParameters instance can be passed when instantiating a Query.
 * In XML mapping files QueryParameters instances are accessible through 
 * params property of Query instances.
 * 
 * 
 * @author Bassam Al-Sarori
 *
 */
public interface QueryParameters {
}
