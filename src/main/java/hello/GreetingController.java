package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private Greeting x;
    
    @Autowired
    private EntityManagerFactory em;
    
	@RequestMapping("/actors")
    public List<Actor> allActors(){
    	return em .createEntityManager().createQuery("from Actor").getResultList();
    }
    
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//    	Using instantiated greeting object
//        return new Greeting(counter.incrementAndGet(),
//                            String.format(template, name));
//    	Using return x
        return x;
    }
    
    @RequestMapping("/data")
    public List<String> dataNegara(@RequestParam("pre") int s){
    	List<String> data = new ArrayList<>();
    	data.add("Indonesia");
    	data.add("Malaysia");
    	data.add("Brunei");
    	data.add("Timor Leste");
    	
    	return data.stream().filter(line -> line.length() > s).collect(Collectors.toList());
    }
    
    @RequestMapping("/countries")
    public String getCountries() throws IOException{
    	URL url = new URL("http://www.webservicex.net/country.asmx/GetCountries");
    	URLConnection connection = url.openConnection();
//    	For POST method
    	connection.setDoOutput(true);
    	connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    	connection.setRequestProperty("Content-Length", "0");
    	
    	InputStream stream = connection.getInputStream();
    	InputStreamReader reader = new InputStreamReader(stream);
    	BufferedReader buffer = new BufferedReader(reader);
    	
    	String line;
    	StringBuilder builder = new StringBuilder();
    	while((line = buffer.readLine()) != null) {
    		builder.append(line);
    	}
    	return builder.toString();

    }
}
