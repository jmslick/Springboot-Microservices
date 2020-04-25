package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SentenceController {

	//@Autowired DiscoveryClient client;
	@Autowired RestTemplate restTemplate;
	
	@GetMapping("/sentence")
	public @ResponseBody String getSentence() {
	  return String.format("%s %s %s %s %s.",
		  getWord("LAB-4-SUBJECT-SERVICE"),
		  getWord("LAB-4-VERB-SERVICE"),
		  getWord("LAB-4-ARTICLE-SERVICE"),
		  getWord("LAB-4-ADJECTIVE-SERVICE"),
		  getWord("LAB-4-NOUN-SERVICE") );
	}

	// for use with DiscoveryClient
	/*public String getWord(String service) {
        List<ServiceInstance> list = client.getInstances(service);		
        if (list != null && list.size() > 0 ) {
      	URI uri = list.get(0).getUri();
	      	if (uri !=null ) {
	      		return (new RestTemplate()).getForObject(uri,String.class);	      		
	      	}
        }
        return null;
	}*/
	
	public String getWord(String service) {
		return restTemplate.getForObject("http://" + service, String.class);
	}
}
