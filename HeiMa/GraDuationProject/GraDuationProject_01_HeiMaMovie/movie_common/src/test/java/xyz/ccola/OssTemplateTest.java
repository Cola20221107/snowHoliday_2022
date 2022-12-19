package xyz.ccola;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xyz.ccola.common.OssTemplate;

/** 
* OssTemplate Tester. 
* 
* @author Cola
* @since <pre>12月 19, 2022</pre> 
* @version 1.0 
*/
@Slf4j
public class OssTemplateTest { 

@Before
public void before() throws Exception {
    log.info("开始测试 ");
} 

@After
public void after() throws Exception {
    log.info("测试结束 ");
} 

/** 
* 
* Method: upload(String fileName, InputStream inputStream) 
* 
*/ 
@Test
public void testUpload() throws Exception { 
//TODO: URL testUpload
} 

/** 
* 
* Method: getContentType(String FilenameExtension) 
* 
*/ 
@Test
public void testGetContentType() throws Exception {
    OssTemplate ossTemplate = new OssTemplate();
    String[] images = new  String[]{
            ".bmp",
            ".gif",
            ".jpeg",".jpg",".png",
            ".aaa"
    };
    for (String image : images) {
        System.out.println(ossTemplate.getContentType(image));
    }
    log.info("testGetContentType 测试通过");
} 


} 
