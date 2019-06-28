# caesarEncrypt
Simple caesar encryption tool with command line options

Command line arguements must include:

  -key (int) => Amount of desired shift
  
  -mode (String) => "enc" for encrypt, "dec" for decrypt
  
  -in => (String) => Path to input text file
  
  -data => (String) => String to be encrypted/decrypted
  
      IF -in OR -data FLAG IS NOT DETECTED USER WILL BE PROMPTED FOR INPUT ON NEXT LINE
      
  -out => (String) Path to output file ["stout" prints output to standard out]
  
  -alg => (String) "unicode" conducts a full unicode caesar shift; "shift" conducts a rotational shift on the lowercase alphabet and preserves not alpha characters
