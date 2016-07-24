/*
 * Copyright (C) 2016 Oleg Kan, @Simplaapliko
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

package com.simplaapliko.updater;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CompareVersionsTestCase {
    
    /* larger */
    
    @Test
    public void compareVersionShouldBeLarger() {
        String version1 = "14";
        String version2 = "11";
        int expected = 1;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeLarger2() {
        String version1 = "11.21";
        String version2 = "11.19";
        int expected = 1;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeLarger3() {
        String version1 = "11.19.1";
        String version2 = "11.19";
        int expected = 1;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeLarger4() {
        String version1 = "11.19.1";
        String version2 = "11";
        int expected = 1;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeLarger5() {
        String version1 = "11.19.1";
        String version2 = null;
        int expected = 1;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeLarger6() {
        String version1 = "11.19";
        String version2 = null;
        int expected = 1;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeLarger7() {
        String version1 = "11";
        String version2 = null;
        int expected = 1;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    /* smaller */
    
    @Test
    public void compareVersionShouldBeSmaller() {
        String version1 = "11";
        String version2 = "14";
        int expected = -1;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeSmaller2() {
        String version1 = "11.25";
        String version2 = "11.39";
        int expected = -1;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeSmaller3() {
        String version1 = "11";
        String version2 = "11.39";
        int expected = -1;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeSmaller4() {
        String version1 = "11";
        String version2 = "11.39.3";
        int expected = -1;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeSmaller5() {
        String version1 = null;
        String version2 = "11.39.3";
        int expected = -1;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    /* equal */
    
    @Test
    public void compareVersionShouldBeEqual() {
        String version1 = "11";
        String version2 = "11";
        int expected = 0;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeEqual2() {
        String version1 = "11.57";
        String version2 = "11.57";
        int expected = 0;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeEqual3() {
        String version1 = "11.7.90";
        String version2 = "11.7.90";
        int expected = 0;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    @Test
    public void compareVersionShouldBeEqual4() {
        String version1 = null;
        String version2 = null;
        int expected = 0;
        
        int result = Versions.compareVersion(version1, version2);
        
        assertThat(result, is(expected));
    }
    
    /* fail */
    
    @Test
    public void compareVersionShouldThtowIllegalArgumentException() {
        String version1 = "";
        String version2 = "";
        int expected = 0;
        
        try {
            int result = Versions.compareVersion(version1, version2);
            assertThat(result, is(expected));
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void compareVersionShouldThtowIllegalArgumentException2() {
        String version1 = "";
        String version2 = "1";
        int expected = 0;
        
        try {
            int result = Versions.compareVersion(version1, version2);
            assertThat(result, is(expected));
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void compareVersionShouldThtowIllegalArgumentException3() {
        String version1 = "12.5";
        String version2 = "";
        int expected = 0;
        
        try {
            int result = Versions.compareVersion(version1, version2);
            assertThat(result, is(expected));
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void compareVersionShouldNotThtowIllegalArgumentException2() {
        String version1 = null;
        String version2 = null;
        int expected = 0;
        
        try {
            int result = Versions.compareVersion(version1, version2);
            assertThat(result, is(expected));
        } catch (IllegalArgumentException e) {
            fail("should not throw IllegalArgumentException");
        }
    }
    
}
