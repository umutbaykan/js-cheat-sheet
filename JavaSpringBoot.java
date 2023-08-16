public class JavaSpringBoot {
  public static void main(String[] args) {
    
    ////// Route handling
    // check out @GetMapping, @PostMapping etc.
    @RequestMapping("/")
    public @ResponseBody String greeting() {
      return "Hello, World";
    }
 
    ////// Testing
    @Test
    @WithMockUser
    void WhenLoggedIn_AndThereAreNoPlaylists_PlaylistsIndexReturnsNoTracks() throws Exception {
      mvc.perform(MockMvcRequestBuilders.get("/api/playlists").contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          // Remember, json path is accessing the keys of an object
          // so if you want to access a specific key / value pair ot run asseritons on
          // $.theKeyYouWant will get you the key
          // Can also use array indexing, $[0]
          // Also chaining $[0].title etc. 
          .andExpect(jsonPath("$", hasSize(0)));
    }
 
 
  }
}