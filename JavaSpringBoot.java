public class JavaSpringBoot {
  public static void main(String[] args) {
    
    ////// Route handling
    // check out @GetMapping, @PostMapping etc.
    @RequestMapping("/")
    public @ResponseBody String greeting() {
      return "Hello, World";
    }
 
    ////// Testing
    
 
 
  }
}