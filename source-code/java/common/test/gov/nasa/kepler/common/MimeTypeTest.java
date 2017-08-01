/*
 * Copyright 2017 United States Government as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 * All Rights Reserved.
 * 
 * This file is available under the terms of the NASA Open Source Agreement
 * (NOSA). You should have received a copy of this agreement with the
 * Kepler source code; see the file NASA-OPEN-SOURCE-AGREEMENT.doc.
 * 
 * No Warranty: THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY
 * WARRANTY OF ANY KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY,
 * INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE
 * WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR FREEDOM FROM
 * INFRINGEMENT, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL BE ERROR
 * FREE, OR ANY WARRANTY THAT DOCUMENTATION, IF PROVIDED, WILL CONFORM
 * TO THE SUBJECT SOFTWARE. THIS AGREEMENT DOES NOT, IN ANY MANNER,
 * CONSTITUTE AN ENDORSEMENT BY GOVERNMENT AGENCY OR ANY PRIOR RECIPIENT
 * OF ANY RESULTS, RESULTING DESIGNS, HARDWARE, SOFTWARE PRODUCTS OR ANY
 * OTHER APPLICATIONS RESULTING FROM USE OF THE SUBJECT SOFTWARE.
 * FURTHER, GOVERNMENT AGENCY DISCLAIMS ALL WARRANTIES AND LIABILITIES
 * REGARDING THIRD-PARTY SOFTWARE, IF PRESENT IN THE ORIGINAL SOFTWARE,
 * AND DISTRIBUTES IT "AS IS."
 * 
 * Waiver and Indemnity: RECIPIENT AGREES TO WAIVE ANY AND ALL CLAIMS
 * AGAINST THE UNITED STATES GOVERNMENT, ITS CONTRACTORS AND
 * SUBCONTRACTORS, AS WELL AS ANY PRIOR RECIPIENT. IF RECIPIENT'S USE OF
 * THE SUBJECT SOFTWARE RESULTS IN ANY LIABILITIES, DEMANDS, DAMAGES,
 * EXPENSES OR LOSSES ARISING FROM SUCH USE, INCLUDING ANY DAMAGES FROM
 * PRODUCTS BASED ON, OR RESULTING FROM, RECIPIENT'S USE OF THE SUBJECT
 * SOFTWARE, RECIPIENT SHALL INDEMNIFY AND HOLD HARMLESS THE UNITED
 * STATES GOVERNMENT, ITS CONTRACTORS AND SUBCONTRACTORS, AS WELL AS ANY
 * PRIOR RECIPIENT, TO THE EXTENT PERMITTED BY LAW. RECIPIENT'S SOLE
 * REMEDY FOR ANY SUCH MATTER SHALL BE THE IMMEDIATE, UNILATERAL
 * TERMINATION OF THIS AGREEMENT.
 */

package gov.nasa.kepler.common;

import static org.junit.Assert.assertEquals;
import gov.nasa.kepler.common.MimeType;

import org.junit.Test;

/**
 * Tests the {@link MimeType} class.
 * 
 * @author Bill Wohler
 * @author Forrest Girouard
  */
public class MimeTypeTest {

    @Test
    public void testGetContentType() {
        assertEquals("text/plain", MimeType.PLAIN_TEXT.getContentType());
        assertEquals("application/pdf", MimeType.PDF.getContentType());
        assertEquals("image/gif", MimeType.GIF.getContentType());
        assertEquals("application/octet-stream",
            MimeType.OCTET_STREAM.getContentType());
    }

    @Test
    public void testGetFileExtension() {
        assertEquals(".txt", MimeType.PLAIN_TEXT.getFileExtension());
        assertEquals(".pdf", MimeType.PDF.getFileExtension());
        assertEquals(".gif", MimeType.GIF.getFileExtension());
        assertEquals("", MimeType.OCTET_STREAM.getFileExtension());
    }

    @Test
    public void testValueOfContentType() {
        assertEquals(MimeType.valueOfContentType("text/plain"),
            MimeType.PLAIN_TEXT);
        assertEquals(MimeType.valueOfContentType("application/pdf"),
            MimeType.PDF);
        assertEquals(MimeType.valueOfContentType("image/gif"), MimeType.GIF);
        assertEquals(MimeType.valueOfContentType("application/octet-stream"),
            MimeType.OCTET_STREAM);

        assertEquals(MimeType.valueOfContentType(null), MimeType.OCTET_STREAM);
        assertEquals(MimeType.valueOfContentType(""), MimeType.OCTET_STREAM);
        assertEquals(MimeType.valueOfContentType("fubar"),
            MimeType.OCTET_STREAM);

        assertEquals(MimeType.valueOfContentType("xtext/plain"),
            MimeType.OCTET_STREAM);
        assertEquals(MimeType.valueOfContentType("text/plainx"),
            MimeType.OCTET_STREAM);
    }

    @Test
    public void testValueOfExtension() {
        assertEquals(MimeType.valueOfFileExtension(".txt"), MimeType.PLAIN_TEXT);
        assertEquals(MimeType.valueOfFileExtension(".pdf"), MimeType.PDF);
        assertEquals(MimeType.valueOfFileExtension(".gif"), MimeType.GIF);
        assertEquals(MimeType.valueOfFileExtension(".html_files/px"), MimeType.GIF);

        assertEquals(MimeType.valueOfFileExtension(null), MimeType.OCTET_STREAM);
        assertEquals(MimeType.valueOfFileExtension(""), MimeType.OCTET_STREAM);
        assertEquals(MimeType.valueOfFileExtension(".fubar"), MimeType.OCTET_STREAM);

        assertEquals(MimeType.valueOfFileExtension("txt"), MimeType.OCTET_STREAM);
        assertEquals(MimeType.valueOfFileExtension(".txtx"), MimeType.OCTET_STREAM);
    }
}