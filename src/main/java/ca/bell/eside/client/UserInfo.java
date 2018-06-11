package ca.bell.eside.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
	 * 
	 * UserInfo model with the following properties:
	 * <br><b>sub</b>: the user ID GUID.
	 * <br><b>accounts</b>: this is list of DocuSign accounts associated with the current user.
	 * <br><b>name</b>: the user's full name.
	 * <br><b>givenName</b>: the user's given name.
	 * <br><b>familyName</b>: the user's family name.
	 * <br><b>email</b>: the user's email address.
	 * <br><b>created</b>: the UTC DateTime when the user login was created.
	 *
	 * @see Account
	 *
	 */
	public class UserInfo {
		/**
		 * 
		 * Account model with the following properties:
		 * <br><b>accountId</b>: the account ID GUID.
		 * <br><b>isDefault</b>: whether this is the default account, when the user has access to multiple accounts.
		 * <br><b>accountName</b>: the human-readable name of the account.
		 * <br><b>baseUri</b>: the base URI associated with this account.
		 * It also tells which DocuSign data center the account is hosted on.
		 *
		 */
		public static class Account {
			
			/**
			 * Organization model with the properties:
			 * @author Hariprasath.Ravi
			 *
			 */
			public static class Organization {
				
				/**
				 * Link model with the properties:
				 * @author Hariprasath.Ravi
				 *
				 */
				public static class Link {
					
					@JsonProperty("rel")
					private String rel = null;
					
					@JsonProperty("href")
					private String href = null;
					
					public Link rel(String rel) {
						this.rel = rel;
						return this;
					}

					@ApiModelProperty(example = "null", value = "")
					public String getRel() {
						return rel;
					}

					public void setRel(String rel) {
						this.rel = rel;
					}

					public Link href(String href) {
						this.href = href;
						return this;
					}
					
					@ApiModelProperty(example = "null", value = "")
					public String getHref() {
						return href;
					}

					public void setHref(String href) {
						this.href = href;
					}
					
					@Override
					public boolean equals(java.lang.Object o) {
						if (this == o) {
							return true;
						}
						if (o == null || getClass() != o.getClass()) {
							return false;
						}
						Link link = (Link) o;
						return Objects.equals(this.rel, link.rel)
								&& Objects.equals(this.rel, link.rel);
					}

					@Override
					public int hashCode() {
						return Objects.hash(rel ,href);
					}

					@Override
					public String toString() {
						StringBuilder sb = new StringBuilder();
						sb.append("class Organization {\n");

						sb.append("		rel: ").append(toIndentedString(rel)).append("\n");
						sb.append("		href: ").append(toIndentedString(href)).append("\n");
						return sb.toString();
					}

					/**
					 * Convert the given object to string with each line indented by 4
					 * spaces (except the first line).
					 */
					private String toIndentedString(java.lang.Object o) {
						if (o == null) {
							return "null";
						}
						return o.toString().replace("\n", "\n    ");
					}
					
				}
				
				@JsonProperty("organization_id")
				private String organizationId = null;
				
				@JsonProperty("links")
				private List<Link> links = new ArrayList<UserInfo.Account.Organization.Link>();

				public Organization organizationId(String organizationId) {
					this.organizationId = organizationId;
					return this;
				}
				
				@ApiModelProperty(example = "null", value = "")
				public String getOrganizationId() {
					return organizationId;
				}

				public void setOrganizationId(String organizationId) {
					this.organizationId = organizationId;
				}

				public Organization links(List<Link> links) {
					this.links = links;
					return this;
				}
				
				@ApiModelProperty(example = "null", value = "")
				public List<Link> getLinks() {
					return links;
				}

				public void setLinks(List<Link> links) {
					this.links = links;
				}
				
				@Override
				public boolean equals(java.lang.Object o) {
					if (this == o) {
						return true;
					}
					if (o == null || getClass() != o.getClass()) {
						return false;
					}
					Organization organization = (Organization) o;
					return Objects.equals(this.organizationId, organization.organizationId)
							&& Objects.equals(this.links, organization.links);
				}

				@Override
				public int hashCode() {
					return Objects.hash(organizationId, links);
				}

				@Override
				public String toString() {
					StringBuilder sb = new StringBuilder();
					sb.append("class Organization {\n");

					sb.append("		organizationId: ").append(toIndentedString(organizationId)).append("\n");
					sb.append("		links: ").append(toIndentedString(links)).append("\n");
					return sb.toString();
				}

				/**
				 * Convert the given object to string with each line indented by 4
				 * spaces (except the first line).
				 */
				private String toIndentedString(java.lang.Object o) {
					if (o == null) {
						return "null";
					}
					return o.toString().replace("\n", "\n    ");
				}
				
			}
			
			@JsonProperty("organization")
			private Organization organization = null;
			
			@JsonProperty("account_id")
			private String accountId = null;

			@JsonProperty("is_default")
			private String isDefault = null;

			@JsonProperty("account_name")
			private String accountName = null;

			@JsonProperty("base_uri")
			private String baseUri = null;
 
			public Account accountId(String accountId) {
				this.accountId = accountId;
				return this;
			}

			/**
			 * Get accountId
			 *
			 * @return accountId
			 **/
			@ApiModelProperty(example = "null", value = "")
			public String getAccountId() {
				return accountId;
			}

			public void setAccountId(String accountId) {
				this.accountId = accountId;
			}

			public Account isDefault(String isDefault) {
				this.isDefault = isDefault;
				return this;
			}

			/**
			 * Get isDefault
			 *
			 * @return isDefault
			 **/
			@ApiModelProperty(example = "null", value = "")
			public String getIsDefault() {
				return isDefault;
			}

			public void setIsDefault(String isDefault) {
				this.isDefault = isDefault;
			}

			public Account accountName(String accountName) {
				this.accountName = accountName;
				return this;
			}

			/**
			 * Get accountName
			 * 
			 * @return accountName
			 **/
			@ApiModelProperty(example = "null", value = "")
			public String getAccountName() {
				return accountName;
			}

			public void setAccountName(String accountName) {
				this.accountName = accountName;
			}

			public Account baseUri(String baseUri) {
				this.baseUri = baseUri;
				return this;
			}

			/**
			 * Get baseUri
			 * 
			 * @return baseUri
			 **/
			@ApiModelProperty(example = "null", value = "")
			public String getBaseUri() {
				return baseUri;
			}

			public void setBaseUri(String baseUri) {
				this.baseUri = baseUri;
			}
 
			public Account organization(Organization organization) {
				this.organization = organization;
				return this;
			}
			
			@ApiModelProperty(example = "null", value = "")
			public Organization getOrganization() {
				return organization;
			}

			public void setOrganization(Organization organization) {
				this.organization = organization;
			}

			@Override
			public boolean equals(java.lang.Object o) {
				if (this == o) {
					return true;
				}
				if (o == null || getClass() != o.getClass()) {
					return false;
				}
				Account account = (Account) o;
				return Objects.equals(this.accountId, account.accountId)
						&& Objects.equals(this.isDefault, account.isDefault)
						&& Objects.equals(this.accountName, account.accountName)
						&& Objects.equals(this.baseUri, account.baseUri)
						&& Objects.equals(this.organization, account.organization);
			}

			@Override
			public int hashCode() {
				return Objects.hash(accountId, isDefault, accountName, baseUri, organization);
			}

			@Override
			public String toString() {
				StringBuilder sb = new StringBuilder();
				sb.append("class Account {\n");

				sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
				sb.append("    isDefault: ").append(toIndentedString(isDefault)).append("\n");
				sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
				sb.append("    baseUri: ").append(toIndentedString(baseUri)).append("\n");
				sb.append("		organization: ").append(toIndentedString(organization)).append("\n");
				return sb.toString();
			}

			/**
			 * Convert the given object to string with each line indented by 4
			 * spaces (except the first line).
			 */
			private String toIndentedString(java.lang.Object o) {
				if (o == null) {
					return "null";
				}
				return o.toString().replace("\n", "\n    ");
			}
		}

		@JsonProperty("sub")
		private String sub = null;

		@JsonProperty("email")
		private String email = null;

		@JsonProperty("accounts")
		private java.util.List<Account> accounts = new java.util.ArrayList<Account>();

		@JsonProperty("name")
		private String name = null;

		@JsonProperty("given_name")
		private String givenName = null;

		@JsonProperty("family_name")
		private String familyName = null;

		@JsonProperty("created")
		private String created = null;
		
		public UserInfo sub(String sub) {
			this.sub = sub;
			return this;
		}

		/**
		 * Get sub
		 *
		 * @return sub
		 **/
		@ApiModelProperty(example = "null", value = "")
		public String getSub() {
			return sub;
		}

		public void setSub(String sub) {
			this.sub = sub;
		}

		public UserInfo email(String email) {
			this.email = email;
			return this;
		}

		/**
		 * Get email
		 *
		 * @return email
		 **/
		@ApiModelProperty(example = "null", value = "")
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public UserInfo accounts(java.util.List<Account> accounts) {
			this.accounts = accounts;
			return this;
		}

		public UserInfo addAccountsItem(Account accountsItem) {
			this.accounts.add(accountsItem);
			return this;
		}

		/**
		 * Get accounts
		 * 
		 * @return accounts
		 **/
		@ApiModelProperty(example = "null", value = "")
		public java.util.List<Account> getAccounts() {
			return accounts;
		}

		public void setAccounts(java.util.List<Account> accounts) {
			this.accounts = accounts;
		}

		public UserInfo name(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Get name
		 *
		 * @return name
		 **/
		@ApiModelProperty(example = "null", value = "")
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public UserInfo givenName(String givenName) {
			this.givenName = givenName;
			return this;
		}

		/**
		 * Get givenName
		 *
		 * @return givenName
		 **/
		@ApiModelProperty(example = "null", value = "")
		public String getGivenName() {
			return givenName;
		}

		public void setGivenName(String givenName) {
			this.givenName = givenName;
		}

		public UserInfo familyName(String familyName) {
			this.familyName = familyName;
			return this;
		}

		/**
		 * Get familyName
		 *
		 * @return familyName
		 **/
		@ApiModelProperty(example = "null", value = "")
		public String getFamilyName() {
			return familyName;
		}

		public void setFamilyName(String familyName) {
			this.familyName = familyName;
		}

		public UserInfo created(String created) {
			this.created = created;
			return this;
		}

		/**
		 * Get created
		 *
		 * @return created
		 **/
		@ApiModelProperty(example = "null", value = "")
		public String getCreated() {
			return created;
		}

		public void setCreated(String created) {
			this.created = created;
		}

		@Override
		public boolean equals(java.lang.Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			UserInfo userInfo = (UserInfo) o;
			return Objects.equals(this.sub, userInfo.sub) && Objects.equals(this.email, userInfo.email)
					&& Objects.equals(this.accounts, userInfo.accounts) && Objects.equals(this.name, userInfo.name)
					&& Objects.equals(this.givenName, userInfo.givenName) && Objects.equals(this.familyName, userInfo.familyName)
					&& Objects.equals(this.created, userInfo.created);
		}

		@Override
		public int hashCode() {
			return Objects.hash(sub, email, accounts, name, givenName, familyName, created);
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("class UserInfo {\n");

			sb.append("    sub: ").append(toIndentedString(sub)).append("\n");
			sb.append("    email: ").append(toIndentedString(email)).append("\n");
			sb.append("    name: ").append(toIndentedString(name)).append("\n");
			sb.append("    givenName: ").append(toIndentedString(givenName)).append("\n");
			sb.append("    familyName: ").append(toIndentedString(familyName)).append("\n");
			sb.append("    created: ").append(toIndentedString(created)).append("\n");
			sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
			return sb.toString();
		}

		/**
		 * Convert the given object to string with each line indented by 4 spaces
		 * (except the first line).
		 */
		private String toIndentedString(java.lang.Object o) {
			if (o == null) {
				return "null";
			}
			return o.toString().replace("\n", "\n    ");
		}

	}
