package com.olaelectric.Controller;

import com.olaelectric.ShellScript.Scripting;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetController {


    @RequestMapping(method = RequestMethod.GET, value = "/test/{version}/hello")
    public String getController(@PathVariable(value = "version") String version,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "age") String age){
        return "hello world .... this is "+name+" of age "+age+" logged into version "+version;
    }

    @RequestMapping("/hello")
    public String sayHello(){
        return "hello world!";
    }

    @RequestMapping("/sendCommand")
    public String publishCommand(){
        Scripting scripter = new Scripting();
        scripter.publishCommand();
        return "Success";
    }

}

