package com.harsh.studentapp.users.controller;
import java.io.IOException;

import org.springframework.web.bind.annotation.RestController;

import com.harsh.studentapp.users.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Delete;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Update;

@RestController
@RequestMapping("/api/elastic")
public class EsController {
	@Autowired
	private Environment env;
	
	
	JestClient client = null;

	public JestClient getClient() {
		if (this.client == null) {
			JestClientFactory factory = new JestClientFactory();
			factory.setHttpClientConfig(new HttpClientConfig.Builder(
					env.getProperty("bezkoder.elastic.link"))
							.multiThreaded(true).defaultMaxTotalConnectionPerRoute(2).maxTotalConnection(10).build());
			this.client = factory.getObject();
			return factory.getObject();
		}
		return this.client;

	}

	@PostMapping("/save")
	public String saveUser(@RequestBody User user) throws IOException {
	
		JestClient client = this.getClient();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode userNode = mapper.createObjectNode().put("name", user.getUsername()).put("email", user.getEmail());
		JestResult postResult = client
				.execute(new Index.Builder(userNode.toString()).index("hes_user").type("user").build());

		return postResult.toString();
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> findUser(@PathVariable final String id) throws IOException {

		JestClient client = this.getClient();
		JestResult getResult = client.execute(new Get.Builder("hes_user", id).type("user").build());
		return ResponseEntity.ok(getResult.toString());
	}

	@PutMapping("/update/{id}")
	public String updateUser(@PathVariable final String id, @RequestBody User user) throws IOException {
	
		JestClient client = this.getClient();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode userNode = mapper.createObjectNode().put("name", user.getUsername()).put("email", user.getEmail());
		JestResult putResult = client.execute(new Update.Builder(userNode.toString()).index("hes_user").id(id).build());

		return putResult.toString();
	}

	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable final String id) throws IOException {
	
		JestClient client = this.getClient();

		JestResult deleteResult = client.execute(new Delete.Builder(id).index("hes_user").type("user").build());
		return deleteResult.toString();
	}
}