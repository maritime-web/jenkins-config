
import hudson.model.*
import jenkins.model.*
import hudson.security.*
import jenkins.security.s2m.AdminWhitelistRule
import com.google.common.collect.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.plugins.credentials.impl.*
import io.jenkins.blueocean.blueocean_github_pipeline.*
import io.jenkins.blueocean.rest.impl.pipeline.credential.*
import org.jenkinsci.plugins.github_branch_source.*
import io.jenkins.blueocean.credential.*


def instance = Jenkins.instance

println "CREATING USERS..."
//Create users
HudsonPrivateSecurityRealm hudsonRealm = new HudsonPrivateSecurityRealm(false)
def authenticatedUser = hudsonRealm.createAccount("PipelineCreator", "admin") //hide

instance.setSecurityRealm(hudsonRealm)

FullControlOnceLoggedInAuthorizationStrategy strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(true)
instance.setAuthorizationStrategy(strategy)
instance.save()
authenticatedUser.save()
instance.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false)

println "ADDING GITHUB CREDENTIALS TO USER..."

//add github credentials to user
def accessToken = "todo..."
def credentialDescription = "GitHub Access Token"
def credentialId = GithubCredentialUtils.computeCredentialId(null, GithubScm.ID, GitHubSCMSource.GITHUB_URL)
UsernamePasswordCredentialsImpl credential = new UsernamePasswordCredentialsImpl(CredentialsScope.USER, credentialId, credentialDescription, authenticatedUser.getId(), accessToken)
CredentialsUtils.createCredentialsInUserStore(
        credential, authenticatedUser, "blueocean-github-domain",
        ImmutableList.<DomainSpecification>of(new BlueOceanDomainSpecification()))

println authenticatedUser.getId()


println "CREATE PIPELINES..."
// CREATE PIPELINES
def map = new HashMap<String, Object>()
map.put("repoOwner", "maritime-web")
map.put("repository", "Enav-Services")
def jsonConfig = net.sf.json.JSONObject.fromObject(map)
io.jenkins.blueocean.rest.model.BlueScmConfig blueConfig = new io.jenkins.blueocean.rest.model.BlueScmConfig("github", "https://api.github.com", "todo...", jsonConfig)

def name = "Enav-Services"

def parent = Jenkins.instance

def descriptorName = "org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject"
//def descriptorClass = jenkins.branch.MultiBranchProjectDescriptor.class


def descriptor = hudson.model.Items.all().findByName(descriptorName)

def project = parent.createProject(descriptor, name)

//Source
def strategies = new HashSet<>()
strategies.add(jenkins.scm.api.mixin.ChangeRequestCheckoutStrategy.MERGE)

def source = new org.jenkinsci.plugins.github_branch_source.GitHubSCMSourceBuilder(null,
        blueConfig.getUri(),
        blueConfig.getCredentialId(),
        (String)blueConfig.getConfig().get("repoOwner"),
        (String)blueConfig.getConfig().get("repository"))
        .withTrait(new org.jenkinsci.plugins.github_branch_source.BranchDiscoveryTrait(true, false))
        .withTrait(new org.jenkinsci.plugins.github_branch_source.ForkPullRequestDiscoveryTrait(strategies, new org.jenkinsci.plugins.github_branch_source.ForkPullRequestDiscoveryTrait.TrustPermission()))
        .withTrait(new jenkins.plugins.git.traits.CleanBeforeCheckoutTrait())
        .withTrait(new jenkins.plugins.git.traits.CleanAfterCheckoutTrait())
        .withTrait(new jenkins.plugins.git.traits.LocalBranchTrait())
        .build()
def sourceList = new ArrayList<jenkins.branch.BranchSource>()
sourceList.add(new jenkins.branch.BranchSource(source))
project.setSourcesList(sourceList)
project.save()

project.scheduleBuild()
//credentials
//def blueCred = new BlueOceanCredentialsProvider.FolderPropertyImpl()