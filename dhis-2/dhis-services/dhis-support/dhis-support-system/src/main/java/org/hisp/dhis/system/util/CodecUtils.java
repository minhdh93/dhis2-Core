package org.hisp.dhis.system.util;

import java.util.Base64;

/*
 * Copyright (c) 2004-2016, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * Utility class for encoding and decoding operations.
 * 
 * @author Lars Helge Overland
 */
public class CodecUtils
{
    private static final String ILLEGAL_FILENAME_CHARS_REGEX = "[/\\?%*:|\"'<>.]";
    
    /**
     * Encodes the given string by removing chars which are illegal on most file 
     * systems.
     * 
     * @param string the string to encode.
     * @return an encoded filename string.
     */
    public static String filenameEncode( String string )
    {
        if ( string != null )
        {
            string = string.replaceAll( ILLEGAL_FILENAME_CHARS_REGEX, "" );
            
            if ( string.length() > 255 )
            {
                string = string.substring( 0, 255 );
            }
        }
        
        return string;
    }
    
    /**
     * Returns a basic authentication string which is generated by prepending 
     * "Basic " and base64-encoding username:password.
     * 
     * @param username the username to use for authentication.
     * @param password the password to use for authentication.
     * @return the encoded string.
     */
    public static String getBasicAuthString( String username, String password )
    {
        String string = username + ":" + password;
        
        return "Basic " + Base64.getEncoder().encodeToString( string.getBytes() );
    }    
}