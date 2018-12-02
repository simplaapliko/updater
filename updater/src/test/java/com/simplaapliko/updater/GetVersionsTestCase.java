/*
 * Copyright (C) 2016 Oleg Kan
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
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

@SuppressWarnings("ConstantConditions")
public class GetVersionsTestCase {
    
    /* major */
    
    @Test
    public void majorVersionShouldBeValid() {
        String version = "7.3.2";
        String expected = "7";
        
        String actual = Versions.getMajorVersion(version);
        
        assertThat(actual, is(expected));
    }
    
    @Test
    public void majorVersionShouldBeInvalid() {
        String version = "7.3.2";
        String unexpected = "2";
        
        String actual = Versions.getMajorVersion(version);
        
        assertThat(actual, is(not(unexpected)));
    }
    
    @Test
    public void majorVersionShouldBeNull() {
        String version = "";
        String expected = null;
        
        String majorVersion = Versions.getMajorVersion(version);
        
        assertThat(majorVersion, is(expected));
    }
    
    @Test
    public void majorVersionShouldBeNull2() {
        String version = null;
        String expected = null;
        
        String majorVersion = Versions.getMajorVersion(version);
        
        assertThat(majorVersion, is(expected));
    }
    
    /* minor */
    
    @Test
    public void minorVersionShouldBeValid() {
        String version = "7.3.2";
        String expected = "7.3";
        
        String actual = Versions.getMinorVersion(version);
        
        assertThat(actual, is(expected));
    }
    
    @Test
    public void minorVersionShouldBeInvalid() {
        String version = "7.3.2";
        String unexpected = "2";
        
        String actual = Versions.getMinorVersion(version);
        
        assertThat(actual, is(not(unexpected)));
    }
    
    @Test
    public void minorVersionShouldBeNull() {
        String version = "";
        String expected = null;
        
        String majorVersion = Versions.getMinorVersion(version);
        
        assertThat(majorVersion, is(expected));
    }
    
    @Test
    public void minorVersionShouldBeNull2() {
        String version = null;
        String expected = null;
        
        String majorVersion = Versions.getMinorVersion(version);
        
        assertThat(majorVersion, is(expected));
    }
    
    @Test
    public void minorVersionShouldBeNull3() {
        String version = "21";
        String expected = null;
        
        String majorVersion = Versions.getMinorVersion(version);
        
        assertThat(majorVersion, is(expected));
    }
    
    /* patch */
    
    @Test
    public void patchVersionShouldBeValid() {
        String version = "7.3.2";
        String expected = "7.3.2";
        
        String actual = Versions.getPatchVersion(version);
        
        assertThat(actual, is(expected));
    }
    
    @Test
    public void patchVersionShouldBeInvalid() {
        String version = "7.3.2";
        String unexpected = "9";
        
        String actual = Versions.getPatchVersion(version);
        
        assertThat(actual, is(not(unexpected)));
    }
    
    @Test
    public void patchVersionShouldBeNull() {
        String version = "";
        String expected = null;
        
        String majorVersion = Versions.getPatchVersion(version);
        
        assertThat(majorVersion, is(expected));
    }
    
    @Test
    public void patchVersionShouldBeNull2() {
        String version = null;
        String expected = null;
        
        String majorVersion = Versions.getPatchVersion(version);
        
        assertThat(majorVersion, is(expected));
    }
    
    @Test
    public void patchVersionShouldBeNull3() {
        String version = "14.12";
        String expected = null;
        
        String majorVersion = Versions.getPatchVersion(version);
        
        assertThat(majorVersion, is(expected));
    }
    
    @Test
    public void patchVersionShouldBeNull4() {
        String version = "14";
        String expected = null;
        
        String majorVersion = Versions.getPatchVersion(version);
        
        assertThat(majorVersion, is(expected));
    }
}
