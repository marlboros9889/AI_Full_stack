package com.the703.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@Getter @Setter @ToString  
@NoArgsConstructor
@AllArgsConstructor
public class Sboard2Dto {
   private int id;
   private int appUserId;
   private String btitle;
   private String bcontent;
   private String bpass;
   private String bfile;
   private int    bhit;
   private String bip;
   private String createdAt;
}

