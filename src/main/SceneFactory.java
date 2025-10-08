package main;

import java.util.Map;
import java.util.HashMap;

/**
 * Generate the story of the game here by creating scenes and connecting them with verbs and choices together
 */
public class SceneFactory {

    public static Map<String, Scene> buildAllScenes() {
        Map<String, Scene> scenes = new HashMap<>();

        java.util.function.Function<Scene, Scene> add = (s) -> {
            scenes.put(s.getId(), s);
            return s;
        };

        // ============ PROLOGUE ============

        Scene s01 = add.apply(new Scene("S01_Prologue", "🌏 The Four Nations",
                "Long ago, the four nations lived in harmony. Then everything changed.\n" +
                        "You are born with the gift of bending. Your destiny awaits...\n"));
        s01.addChoice("", "", "S02_ChooseElement");

        // ============ ELEMENT SELECTION ============

        add.apply(new Scene("S02_ChooseElement", "⚡ Choose Your Path",
                "The elders gather. Four paths shimmer before you:\n\n" +
                        "🔥 FIRE - Forge blazing infernos, lightning strikes, explosive power\n" +
                        "💧 WATER - Control tides, freeze enemies, heal the wounded\n" +
                        "🌍 EARTH - Shake mountains, forge metal, sense vibrations\n" +
                        "💨 AIR - Summon tornadoes, fly on wind, create vacuums\n\n" +
                        "Which element resonates with your soul?"));
        scenes.get("S02_ChooseElement").addChoice("choose", "fire", "S03_FireAwakening");
        scenes.get("S02_ChooseElement").addChoice("choose", "water", "S03_WaterAwakening");
        scenes.get("S02_ChooseElement").addChoice("choose", "earth", "S03_EarthAwakening");
        scenes.get("S02_ChooseElement").addChoice("choose", "air", "S03_AirAwakening");

        // ============ ELEMENT-SPECIFIC AWAKENINGS ============

        Scene fireAwaken = add.apply(new Scene("S03_FireAwakening", "🔥 Flames Ignite Within",
                "WHOOSH! Fire erupts from your fingertips!\n" +
                        "The flames dance wildly - dangerous, beautiful, alive.\n" +
                        "You are a Firebender. Passion and fury flow through your veins.\n" +
                        "The elders watch nervously. Fire destroyed much in the great war..."));
        fireAwaken.addChoice("", "", "S04_FireTraining");

        Scene waterAwaken = add.apply(new Scene("S03_WaterAwakening", "💧 The Tide Answers",
                "The water rises at your command, swirling in graceful arcs.\n" +
                        "You feel every ripple, every current, every drop.\n" +
                        "You are a Waterbender. Adaptability and healing are your gifts.\n" +
                        "The tribe celebrates - the ocean has blessed another warrior."));
        waterAwaken.addChoice("", "", "S04_WaterTraining");

        Scene earthAwaken = add.apply(new Scene("S03_EarthAwakening", "🌍 Stone Trembles",
                "CRACK! The ground splits beneath your feet!\n" +
                        "Rock responds to your will, solid and unyielding.\n" +
                        "You are an Earthbender. Strength and endurance define you.\n" +
                        "The clan roars approval - the earth itself has chosen you."));
        earthAwaken.addChoice("", "", "S04_EarthTraining");

        Scene airAwaken = add.apply(new Scene("S03_AirAwakening", "💨 Wind Rises",
                "The breeze swirls around you, lifting you off your feet!\n" +
                        "Air flows freely, light and boundless.\n" +
                        "You are an Airbender. Freedom and wisdom are your path.\n" +
                        "The monks smile serenely - another nomad joins the sky."));
        airAwaken.addChoice("", "", "S04_AirTraining");

        // ============ ELEMENT-SPECIFIC TRAINING ============

        // FIRE Training
        add.apply(new Scene("S04_FireTraining", "🔥 First Lessons: Control the Flame",
                "Master Roku demonstrates breathing techniques.\n" +
                        "'Fire comes from the breath,' he says. 'Rage makes it wild. Control makes it deadly.'\n" +
                        "Before you: three training dummies. How will you practice?"));
        scenes.get("S04_FireTraining").addChoice("attack", "torch", "S05_AggressiveFire");
        scenes.get("S04_FireTraining").addChoice("meditate", "", "S06_BalancedFire");
        scenes.get("S04_FireTraining").addChoice("inspect", "scroll", "S07_AncientFire");

        // WATER Training
        add.apply(new Scene("S04_WaterTraining", "💧 First Lessons: Flow Like Water",
                "Master Katara shows you the basic forms.\n" +
                        "'Water is the element of change,' she explains. 'We can heal or harm.'\n" +
                        "The training pool shimmers. What calls to you?"));
        scenes.get("S04_WaterTraining").addChoice("help", "monk", "S08_HealingWater");
        scenes.get("S04_WaterTraining").addChoice("attack", "enemy", "S09_CombatWater");
        scenes.get("S04_WaterTraining").addChoice("meditate", "", "S10_SpiritWater");

        // EARTH Training
        add.apply(new Scene("S04_EarthTraining", "🌍 First Lessons: Stand Your Ground",
                "Master Toph pounds the earth with her fists.\n" +
                        "'Earth is the element of substance,' she grunts. 'Be stubborn. Be strong.'\n" +
                        "Boulders surround you. Time to move some rock."));
        scenes.get("S04_EarthTraining").addChoice("attack", "statue", "S11_AggressiveEarth");
        scenes.get("S04_EarthTraining").addChoice("inspect", "ruins", "S12_MetalBending");
        scenes.get("S04_EarthTraining").addChoice("meditate", "", "S13_SeismicSense");

        // AIR Training
        add.apply(new Scene("S04_AirTraining", "💨 First Lessons: Freedom in Motion",
                "Master Aang floats effortlessly above you.\n" +
                        "'Air is the element of freedom,' he laughs. 'Don't think. Just be!'\n" +
                        "The mountain peak awaits. How will you master the wind?"));
        scenes.get("S04_AirTraining").addChoice("climb", "gate", "S14_SpiritualAir");
        scenes.get("S04_AirTraining").addChoice("help", "village", "S15_PacifistAir");
        scenes.get("S04_AirTraining").addChoice("attack", "enemy", "S16_WarriorAir");

        // ============ FIRE PATHS ============

        add.apply(new Scene("S05_AggressiveFire", "🔥 Path of the Dragon",
                "You unleash a torrent of flames! The dummies explode!\n" +
                        "Power surges through you. More. You want MORE.\n" +
                        "Roku frowns. 'Careful. Fire is a weapon, not a toy.'"));
        scenes.get("S05_AggressiveFire").addChoice("grab", "torch", "S17_LightningBending");
        scenes.get("S05_AggressiveFire").addChoice("flee", "", "S18_FireBandit");
        scenes.get("S05_AggressiveFire").addChoice("talk", "mentor", "S06_BalancedFire");

        add.apply(new Scene("S06_BalancedFire", "🔥 Path of the Sun Warrior",
                "You breathe. In and out. The flame follows your breath.\n" +
                        "Small. Controlled. Beautiful. This is mastery.\n" +
                        "Roku nods approvingly. 'Now you understand.'"));
        scenes.get("S06_BalancedFire").addChoice("help", "village", "S19_FireHealer");
        scenes.get("S06_BalancedFire").addChoice("train", "", "S17_LightningBending");
        scenes.get("S06_BalancedFire").addChoice("read", "scroll", "S07_AncientFire");

        add.apply(new Scene("S07_AncientFire", "🔥 Forbidden: Combustion",
                "The scroll depicts a technique lost for centuries: combustion bending.\n" +
                        "Concentrate fire into a single point and... BOOM.\n" +
                        "This power could level cities. Do you dare learn it?"));
        scenes.get("S07_AncientFire").addChoice("read", "scroll", "S20_DarkFire");
        scenes.get("S07_AncientFire").addChoice("meditate", "", "S06_BalancedFire");

        // ============ WATER PATHS ============

        add.apply(new Scene("S08_HealingWater", "💧 Path of the Healer",
                "You place glowing water on the monk's wounds.\n" +
                        "The glow spreads. Flesh knits. Pain fades.\n" +
                        "Katara smiles. 'You have the gift. Cherish it.'"));
        scenes.get("S08_HealingWater").addChoice("help", "allies", "S21_MasterHealer");
        scenes.get("S08_HealingWater").addChoice("walk", "forest", "S22_SpiritOasis");
        scenes.get("S08_HealingWater").addChoice("train", "", "S09_CombatWater");

        add.apply(new Scene("S09_CombatWater", "💧 Path of the Northern Warrior",
                "Ice shards form at your command. Water whips crack like thunder.\n" +
                        "The practice dummy freezes solid, then shatters.\n" +
                        "Katara watches carefully. 'Power without wisdom is dangerous.'"));
        scenes.get("S09_CombatWater").addChoice("attack", "enemy", "S23_BloodBending");
        scenes.get("S09_CombatWater").addChoice("help", "village", "S08_HealingWater");
        scenes.get("S09_CombatWater").addChoice("inspect", "scroll", "S23_BloodBending");

        add.apply(new Scene("S10_SpiritWater", "💧 Path of the Moon Spirit",
                "You meditate by the water. The moon rises.\n" +
                        "Suddenly, you feel it - the pull of the tide in your very blood.\n" +
                        "The spirits whisper secrets of power beyond bending..."));
        scenes.get("S10_SpiritWater").addChoice("talk", "monk", "S22_SpiritOasis");
        scenes.get("S10_SpiritWater").addChoice("meditate", "", "S24_AvatarState");
        scenes.get("S10_SpiritWater").addChoice("help", "village", "S08_HealingWater");

        // ============ EARTH PATHS ============

        add.apply(new Scene("S11_AggressiveEarth", "🌍 Path of the Boulder",
                "WHAM! Your fist meets stone. The statue crumbles!\n" +
                        "Rock flies. Dust swirls. You feel POWERFUL.\n" +
                        "Toph grins. 'Now you're getting it!'"));
        scenes.get("S11_AggressiveEarth").addChoice("attack", "gate", "S25_LavaBinding");
        scenes.get("S11_AggressiveEarth").addChoice("walk", "ruins", "S12_MetalBending");
        scenes.get("S11_AggressiveEarth").addChoice("train", "", "S26_ProBender");

        add.apply(new Scene("S12_MetalBending", "🌍 Discovery: The Metal Clan",
                "In the ruins, you find twisted metal. Ancient armor.\n" +
                        "You reach out. The metal... responds? Impossible!\n" +
                        "Toph's voice echoes: 'Metal is just earth. Purified earth.'"));
        scenes.get("S12_MetalBending").addChoice("grab", "gate", "S27_MetalMaster");
        scenes.get("S12_MetalBending").addChoice("inspect", "ruins", "S28_SandBending");
        scenes.get("S12_MetalBending").addChoice("help", "allies", "S26_ProBender");

        add.apply(new Scene("S13_SeismicSense", "🌍 Path of the Blind Bandit",
                "You close your eyes. Feel the vibrations.\n" +
                        "Every footstep, every heartbeat, every breath - you sense it all.\n" +
                        "This is true earthbending. Seeing without sight."));
        scenes.get("S13_SeismicSense").addChoice("meditate", "", "S24_AvatarState");
        scenes.get("S13_SeismicSense").addChoice("help", "village", "S29_EarthGuardian");
        scenes.get("S13_SeismicSense").addChoice("walk", "forest", "S30_SwampBender");

        // ============ AIR PATHS ============

        add.apply(new Scene("S14_SpiritualAir", "💨 Path of the Monk",
                "You climb without hands, lifted by wind itself.\n" +
                        "At the peak, you touch the sky. Peace fills you.\n" +
                        "Aang appears beside you. 'You're learning detachment. Good.'"));
        scenes.get("S14_SpiritualAir").addChoice("meditate", "", "S24_AvatarState");
        scenes.get("S14_SpiritualAir").addChoice("talk", "monk", "S31_SkyBison");
        scenes.get("S14_SpiritualAir").addChoice("help", "village", "S15_PacifistAir");

        add.apply(new Scene("S15_PacifistAir", "💨 Path of Peace",
                "You use air to gently guide, never to harm.\n" +
                        "Villagers smile as you help rebuild their homes.\n" +
                        "Aang's teachings: 'All life is sacred. Even our enemies.'"));
        scenes.get("S15_PacifistAir").addChoice("help", "allies", "S32_AirNomad");
        scenes.get("S15_PacifistAir").addChoice("walk", "village", "S33_Diplomat");
        scenes.get("S15_PacifistAir").addChoice("meditate", "", "S14_SpiritualAir");

        add.apply(new Scene("S16_WarriorAir", "💨 Path of the Storm",
                "Air CAN be a weapon. You create a vacuum. Your target gasps.\n" +
                        "Aang looks troubled. 'This breaks our code...'\n" +
                        "But sometimes mercy is weakness. Sometimes."));
        scenes.get("S16_WarriorAir").addChoice("attack", "enemy", "S34_FlightMaster");
        scenes.get("S16_WarriorAir").addChoice("flee", "", "S15_PacifistAir");
        scenes.get("S16_WarriorAir").addChoice("train", "", "S34_FlightMaster");

        // ============ ADVANCED FIRE ABILITIES ============

        add.apply(new Scene("S17_LightningBending", "⚡ Lightning Generation",
                "You separate positive from negative. The energy builds.\n" +
                        "CRACK! Lightning erupts from your fingertips!\n" +
                        "The most dangerous firebending technique. You've mastered it."));
        scenes.get("S17_LightningBending").addChoice("attack", "enemy", "S35_FinalBattle");
        scenes.get("S17_LightningBending").addChoice("help", "allies", "S36_WarPrep");
        scenes.get("S17_LightningBending").addChoice("meditate", "", "S24_AvatarState");

        add.apply(new Scene("S18_FireBandit", "🔥 The Outlaw Life",
                "You flee from discipline. Freedom! Power without rules!\n" +
                        "But bandits find you. 'Join us,' they say. 'Burn what you want.'"));
        scenes.get("S18_FireBandit").addChoice("attack", "enemy", "S20_DarkFire");
        scenes.get("S18_FireBandit").addChoice("flee", "", "S19_FireHealer");
        scenes.get("S18_FireBandit").addChoice("talk", "enemy", "S37_Redemption");

        add.apply(new Scene("S19_FireHealer", "🔥 The Sun's Gift",
                "Few know: fire can heal. Not through water's magic, but through warmth.\n" +
                        "You use flames to soothe, to comfort, to restore.\n" +
                        "This is fire at its purest - the gift of the sun."));
        scenes.get("S19_FireHealer").addChoice("help", "allies", "S36_WarPrep");
        scenes.get("S19_FireHealer").addChoice("walk", "village", "S33_Diplomat");

        add.apply(new Scene("S20_DarkFire", "🔥💀 Combustion Mastery",
                "You focus fire into a single point on your forehead.\n" +
                        "Your mind becomes the weapon. BOOM. Explosion at a distance.\n" +
                        "Ultimate power. Ultimate corruption."));
        scenes.get("S20_DarkFire").addChoice("attack", "enemy", "S38_TyrantEnding");
        scenes.get("S20_DarkFire").addChoice("meditate", "", "S37_Redemption");

        // ============ ADVANCED WATER ABILITIES ============

        add.apply(new Scene("S21_MasterHealer", "💧 Hospital of Hope",
                "Your healing grows legendary. The wounded come from everywhere.\n" +
                        "You mend bones, cure disease, even restore lost memories.\n" +
                        "They call you the greatest healer since Avatar Aang."));
        scenes.get("S21_MasterHealer").addChoice("help", "allies", "S36_WarPrep");
        scenes.get("S21_MasterHealer").addChoice("walk", "forest", "S22_SpiritOasis");

        add.apply(new Scene("S22_SpiritOasis", "💧 The Spirit World Beckons",
                "The water glows with otherworldly light.\n" +
                        "Spirits emerge - neither good nor evil, simply... other.\n" +
                        "They offer you a choice: remain human, or transcend..."));
        scenes.get("S22_SpiritOasis").addChoice("talk", "monk", "S24_AvatarState");
        scenes.get("S22_SpiritOasis").addChoice("meditate", "", "S40_EnlightenmentEnding");

        add.apply(new Scene("S23_BloodBending", "💧🩸 Forbidden: Blood Control",
                "Under the full moon, you feel it - the water in living bodies.\n" +
                        "You could control them. Puppets of flesh and blood.\n" +
                        "The most forbidden technique. The darkest power."));
        scenes.get("S23_BloodBending").addChoice("attack", "enemy", "S38_TyrantEnding");
        scenes.get("S23_BloodBending").addChoice("flee", "", "S37_Redemption");

        // ============ AVATAR STATE (CONVERGENCE POINT) ============

        add.apply(new Scene("S24_AvatarState", "✨ The Bridge Between Worlds",
                "All elements flow through you. Past lives awaken.\n" +
                        "Fire. Water. Earth. Air. All one. All yours.\n" +
                        "You are no longer just a bender. You are the Avatar."));
        scenes.get("S24_AvatarState").addChoice("help", "allies", "S36_WarPrep");
        scenes.get("S24_AvatarState").addChoice("meditate", "", "S40_EnlightenmentEnding");
        scenes.get("S24_AvatarState").addChoice("talk", "enemy", "S39_PeaceEnding");

        // ============ ADVANCED EARTH ABILITIES ============

        add.apply(new Scene("S25_LavaBending", "🌋 Path of Fire and Earth",
                "You heat the earth until it melts. Lava flows at your command!\n" +
                        "The rarest earthbending art. Destructive. Beautiful. Terrifying."));
        scenes.get("S25_LavaBending").addChoice("attack", "enemy", "S35_FinalBattle");
        scenes.get("S25_LavaBending").addChoice("help", "village", "S29_EarthGuardian");

        add.apply(new Scene("S26_ProBender", "🌍 Arena Champion",
                "The crowd roars! You dominate the Pro-Bending arena!\n" +
                        "Fame, fortune, glory - all yours!"));
        scenes.get("S26_ProBender").addChoice("help", "allies", "S36_WarPrep");
        scenes.get("S26_ProBender").addChoice("walk", "village", "S33_Diplomat");

        add.apply(new Scene("S27_MetalMaster", "🌍 Steel Guardian",
                "Metal bends to your will. Armor. Weapons. Structures.\n" +
                        "You are one of the few metal benders in existence."));
        scenes.get("S27_MetalMaster").addChoice("help", "allies", "S36_WarPrep");
        scenes.get("S27_MetalMaster").addChoice("attack", "enemy", "S35_FinalBattle");

        add.apply(new Scene("S28_SandBending", "🌍 Desert Phantom",
                "The sand becomes your weapon and shield.\n" +
                        "You create storms, sculptures, entire buildings from sand alone."));
        scenes.get("S28_SandBending").addChoice("walk", "ruins", "S30_SwampBender");
        scenes.get("S28_SandBending").addChoice("help", "village", "S29_EarthGuardian");

        add.apply(new Scene("S29_EarthGuardian", "🌍 Protector of the Land",
                "You use earthbending to protect, not destroy.\n" +
                        "Walls rise to shield the innocent. Earth becomes sanctuary."));
        scenes.get("S29_EarthGuardian").addChoice("help", "allies", "S36_WarPrep");
        scenes.get("S29_EarthGuardian").addChoice("talk", "allies", "S33_Diplomat");

        add.apply(new Scene("S30_SwampBender", "🌍💧 The Swamp's Secret",
                "In the living swamp, you learn to bend the water in plants.\n" +
                        "Vines become weapons. Trees become allies.\n" +
                        "Earth and water blur into one living technique."));
        scenes.get("S30_SwampBender").addChoice("help", "village", "S29_EarthGuardian");
        scenes.get("S30_SwampBender").addChoice("walk", "forest", "S22_SpiritOasis");

        // ============ ADVANCED AIR ABILITIES ============

        add.apply(new Scene("S31_SkyBison", "💨 Bond with the Sky",
                "A sky bison chooses you! The ancient bond forms.\n" +
                        "You and your companion soar across nations.\n" +
                        "Together, you are unstoppable."));
        scenes.get("S31_SkyBison").addChoice("help", "allies", "S32_AirNomad");
        scenes.get("S31_SkyBison").addChoice("walk", "village", "S33_Diplomat");

        add.apply(new Scene("S32_AirNomad", "💨 Keeper of Peace",
                "You travel, helping all. Never taking sides.\n" +
                        "The airbender way: balance, neutrality, compassion."));
        scenes.get("S32_AirNomad").addChoice("help", "allies", "S36_WarPrep");
        scenes.get("S32_AirNomad").addChoice("talk", "enemy", "S39_PeaceEnding");

        add.apply(new Scene("S33_Diplomat", "🕊️ Voice of Reason",
                "Your reputation as peacemaker grows.\n" +
                        "Nations seek your counsel. War may be averted."));
        scenes.get("S33_Diplomat").addChoice("talk", "enemy", "S39_PeaceEnding");
        scenes.get("S33_Diplomat").addChoice("help", "allies", "S36_WarPrep");

        add.apply(new Scene("S34_FlightMaster", "💨 Weightless Freedom",
                "You let go of earthly attachment. Literally.\n" +
                        "Gravity releases you. You FLY without aid.\n" +
                        "True airbender enlightenment achieved."));
        scenes.get("S34_FlightMaster").addChoice("attack", "enemy", "S35_FinalBattle");
        scenes.get("S34_FlightMaster").addChoice("meditate", "", "S40_EnlightenmentEnding");

        // ============ FINAL CONVERGENCE ============

        add.apply(new Scene("S35_FinalBattle", "⚔️ War's End",
                "The final confrontation. All your training leads here.\n" +
                        "Elements clash. The world holds its breath.\n" +
                        "Victory or death. There is no middle ground."));
        scenes.get("S35_FinalBattle").addChoice("attack", "enemy", "S41_VictoryEnding");
        scenes.get("S35_FinalBattle").addChoice("flee", "", "S42_DefeatEnding");

        add.apply(new Scene("S36_WarPrep", "🛡️ Rally the Troops",
                "Allies gather from all nations. Fire. Water. Earth. Air.\n" +
                        "United, you prepare for the battle that will decide everything."));
        scenes.get("S36_WarPrep").addChoice("attack", "enemy", "S35_FinalBattle");
        scenes.get("S36_WarPrep").addChoice("talk", "enemy", "S39_PeaceEnding");

        add.apply(new Scene("S37_Redemption", "💔 The Long Road Back",
                "You've done terrible things. But it's not too late.\n" +
                        "Redemption is hard. Painful. But possible.\n" +
                        "The question is: do you deserve it?"));
        scenes.get("S37_Redemption").addChoice("help", "village", "S33_Diplomat");
        scenes.get("S37_Redemption").addChoice("meditate", "", "S24_AvatarState");

        // ============ ENDINGS ============

        add.apply(new Scene("S38_TyrantEnding", "💀 ENDING: Dark Lord",
                "You rule through fear. Absolute power. Absolute control.\n" +
                        "The world kneels, but calls you monster.\n" +
                        "Was it worth it?\n\n" +
                        "===== DARK LORD ENDING ====="));
        scenes.get("S38_TyrantEnding").addChoice("restart", "game", "S01_Prologue");

        add.apply(new Scene("S39_PeaceEnding", "🕊️ ENDING: The Peacemaker",
                "Words triumphed over weapons. No blood spilled.\n" +
                        "A new era begins. You are the bridge between nations.\n" +
                        "History remembers you as the one who chose mercy.\n\n" +
                        "===== PEACE ENDING ====="));
        scenes.get("S39_PeaceEnding").addChoice("restart", "game", "S01_Prologue");

        add.apply(new Scene("S40_EnlightenmentEnding", "✨ ENDING: Transcendence",
                "You become more than human. A spirit. Eternal.\n" +
                        "The Avatar Cycle ends and begins anew in you.\n" +
                        "Mortal concerns fade. You are balance itself.\n\n" +
                        "===== ENLIGHTENMENT ENDING ====="));
        scenes.get("S40_EnlightenmentEnding").addChoice("restart", "game", "S01_Prologue");

        add.apply(new Scene("S41_VictoryEnding", "⚔️ ENDING: Hero's Glory",
                "You stand victorious. The enemy falls.\n" +
                        "Songs will be sung. Statues will be raised.\n" +
                        "But the scars remain. Heroes pay in blood.\n\n" +
                        "===== VICTORY ENDING ====="));
        scenes.get("S41_VictoryEnding").addChoice("restart", "game", "S01_Prologue");

        add.apply(new Scene("S42_DefeatEnding", "💀 ENDING: Darkness Falls",
                "You fall. The enemy wins. Hope dies.\n" +
                        "Perhaps in another life...\n\n" +
                        "===== DEFEAT ENDING ====="));
        scenes.get("S42_DefeatEnding").addChoice("restart", "game", "S01_Prologue");

        return scenes;
    }
}