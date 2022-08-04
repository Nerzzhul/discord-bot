package space.ebyte.config;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfig {
    private static final Logger logger = LoggerFactory.getLogger(BotConfig.class);

    @Bean
    public JDA jdaConfig(@Value("${discord.bot.token}") String token) throws Exception {
        final JDABuilder builder = JDABuilder.createDefault(token);
        builder.addEventListeners();
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        final JDA jda = builder.build();
        jda.awaitReady();
        return jda;
    }
}
