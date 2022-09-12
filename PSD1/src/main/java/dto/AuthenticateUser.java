package dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthenticateUser {
		
		@NotBlank
		@Email
		@Column(length=30,name="user_email",unique=true,nullable=false)
		private String userEmail;
		@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password")
		@Column(length = 20, nullable = false)
		private String password;
		
		
		
		public AuthenticateUser() {
			super();
			// TODO Auto-generated constructor stub
		}



		public AuthenticateUser(@NotBlank @Email String userEmail,
				@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password") String password) {
			super();
			this.userEmail = userEmail;
			this.password = password;
		}



		public String getUserEmail() {
			return userEmail;
		}



		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}



		public String getPassword() {
			return password;
		}



		public void setPassword(String password) {
			this.password = password;
		}



		@Override
		public String toString() {
			return "AuthenticateUser [userEmail=" + userEmail + ", password=" + password + "]";
		}
		
		
		
}
