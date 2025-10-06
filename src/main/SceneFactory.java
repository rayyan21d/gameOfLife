package main;

import java.util.Map;
import java.util.HashMap;

/**
 * SceneFactory builds and returns all Scene objects keyed by their scene id.
 * Usage: Map<String, Scene> scenes = SceneFactory.buildAllScenes();
 */
public class SceneFactory {

    public static Map<String, Scene> buildAllScenes() {
        Map<String, Scene> scenes = new HashMap<>();

        // Helper to add scene and keep code compact
        java.util.function.Function<Scene, Scene> add = (s) -> {
            scenes.put(s.getId(), s);
            return s;
        };

        // S01
        add.apply(new Scene("S01_StartGame", "Birth of the Bender",
                "The world awakens; you are born into an elemental lineage."));

        // S02
        add.apply(new Scene("S02_ChooseBender", "Choose Bender",
                "Your element is revealed. The elders murmur of destiny."));

        // S03
        add.apply(new Scene("S03_MeetElder", "Naming Ceremony",
                "An elder gives a name and blessing that will follow you."));

        // S04
        add.apply(new Scene("S04_ElementalAwakening", "Elemental Awakening",
                "A first miracle: flame, ripple, quake, or breeze answers your will."));

        // S05
        add.apply(new Scene("S05_ChildhoodIntro", "Childhood Roots",
                "Family and games shape your earliest memories and choices."));

        // S06
        add.apply(new Scene("S06_FirstMentor", "First Mentor Appears",
                "A cloaked figure offers guidance and training."));

        // S07
        add.apply(new Scene("S07_PlayfulTraining", "Playful Training Grounds",
                "You learn through play with other children, discovering empathy and clumsiness."));

        // S08
        add.apply(new Scene("S08_Discipline", "Discipline and Routines",
                "Long hours of practice under a stern mentor build technique."));

        // S09
        add.apply(new Scene("S09_RebelPath", "Independence",
                "You test authority, chafe at rules, and carve your own path."));

        // S10
        add.apply(new Scene("S10_EmpathyPath", "Empathy Growth",
                "Helping villagers teaches you the weight and warmth of community."));

        // S11
        add.apply(new Scene("S11_SkillPath", "Early Skill Unlock",
                "A new ability surfaces; it feels dangerous and exhilarating."));

        // S12
        add.apply(new Scene("S12_MentorSwitchOption", "Crossroads: New Mentor?",
                "Another guide appears whose philosophy challenges your current one."));

        // S13
        add.apply(new Scene("S13_StreetEncounter", "Street Encounter",
                "Thugs, merchants, and odd allies cross your path in the town streets."));

        // S14
        add.apply(new Scene("S14_Scold", "Scolding and Consequence",
                "A parent or elder scolds you; trust is strained."));

        // S15
        add.apply(new Scene("S15_VillageCrisis", "Village Under Siege",
                "Flames and smoke—your village is attacked and everyone looks to you."));

        // S16
        add.apply(new Scene("S16_Aftermath", "Aftermath",
                "Dust settles; choices create long shadows and new responsibilities."));

        // S17
        add.apply(new Scene("S17_HonorPath", "Honor and Reputation",
                "Your reputation grows—some call you hero, others call you enemy."));

        // S18
        add.apply(new Scene("S18_SurvivalPath", "Survival and Flight",
                "Alone in the wild, you learn to survive, bargain, and adapt."));

        // S19
        add.apply(new Scene("S19_CompassionPath", "Protect and Heal",
                "You shelter and heal the vulnerable, gaining loyal allies."));

        // S20
        add.apply(new Scene("S20_SelfDiscoveryStart", "Journey Begins",
                "You leave home in search of truth and a deeper mastery."));

        // S21
        add.apply(new Scene("S21_NewMentorArc", "New Mentor / Hybrid Teachings",
                "A new mentor offers a hybrid technique that reshapes your training."));

        // S22
        add.apply(new Scene("S22_TrainingArc", "Focused Training",
                "Relentless drills, ancient philosophies, and severe trials."));

        // S23
        add.apply(new Scene("S23_SpiritEncounter", "Spirit World Glimpse",
                "A brush with the spirit world leaves you changed and unsettled."));

        // S24
        add.apply(new Scene("S24_InnerVision", "Meditative Insight",
                "In meditation you glimpse prophecy, old wounds, or hidden power."));

        // S25
        add.apply(new Scene("S25_AncientScrolls", "Forbidden Knowledge",
                "An ancient scroll hums; knowledge promises power or corruption."));

        // S26
        add.apply(new Scene("S26_TrialOfCourage", "Trial of Courage",
                "A trial measures your resolve, skill, and the choices you will make."));

        // S27
        add.apply(new Scene("S27_InfiltrateFortress", "Infiltrate Enemy Fortress",
                "Stealth, deceit, or direct assault: choose your way inside the enemy hold."));

        // S28
        add.apply(new Scene("S28_CleanPath", "Steady Growth Path",
                "A steady, disciplined path avoids corruption and yields slow strength."));

        // S29
        add.apply(new Scene("S29_PowerUnlockOrCorrupt", "Power or Corruption",
                "Embrace power and risk corruption, or restrain it and grow slowly."));

        // S30
        add.apply(new Scene("S30_FinalBattlePrep", "Final Preparations",
                "Rally allies, prepare strategy, and set traps before the final fight."));

        // S31
        add.apply(new Scene("S31_FinalBattleDialogue", "Confrontation Dialogue",
                "Face the main antagonist: words may avert bloodshed, or steel must decide fate."));

        // S32
        add.apply(new Scene("S32_DefeatOrVictory", "Final Battle Outcome",
                "Outcomes diverge: victory, defeat, or a cost so high it changes everything."));

        // S33
        add.apply(new Scene("S33_EndingPeace", "Peaceful Resolution",
                "A nonviolent resolution where negotiation and sacrifice restore balance."));

        // S34
        add.apply(new Scene("S34_SacrificeEnding", "Heroic Sacrifice",
                "A selfless choice that may seal the fate of the world or redeem a soul."));

        // S35
        add.apply(new Scene("S35_VictoryPath", "Victory and Legacy",
                "Triumph, choices about leadership, and the forging of a new legacy."));

        // S36
        add.apply(new Scene("S36_LossPath", "Exile and Loss",
                "Defeat’s wake: exile, plots for revenge, or the road to humility."));

        // S37
        add.apply(new Scene("S37_NewLeader", "New Leader Emerges",
                "You rebuild, reform, and decide what kind of leader you will be."));

        // S38
        add.apply(new Scene("S38_EpilogueReflection", "Reflection and Peace",
                "Quiet years of reflection, teaching, and the seeds of a new era."));

        // S39
        add.apply(new Scene("S39_ReplayabilityStart", "Replay Decision",
                "Decide whether to restart, change your bender, or pursue alternate endings."));

        // S40
        add.apply(new Scene("S40_TrueEnding", "Bridge Between Worlds",
                "A rare ending: you become the bridge between mortal and spirit realms."));

        // === Wire transitions: addChoice(verb, noun, nextSceneId) ===
        // S01
        scenes.get("S01_StartGame").addChoice("choose", "bender", "S02_ChooseBender");
        scenes.get("S01_StartGame").addChoice("meet", "elder", "S03_MeetElder");

        // S02
        scenes.get("S02_ChooseBender").addChoice("accept", "fire", "S04_ElementalAwakening");
        scenes.get("S02_ChooseBender").addChoice("accept", "water", "S04_ElementalAwakening");
        scenes.get("S02_ChooseBender").addChoice("accept", "earth", "S04_ElementalAwakening");
        scenes.get("S02_ChooseBender").addChoice("accept", "air", "S04_ElementalAwakening");

        // S03
        scenes.get("S03_MeetElder").addChoice("listen", "elder", "S04_ElementalAwakening");
        scenes.get("S03_MeetElder").addChoice("question", "elder", "S05_ChildhoodIntro");

        // S04
        scenes.get("S04_ElementalAwakening").addChoice("meditate", "element", "S06_FirstMentor");
        scenes.get("S04_ElementalAwakening").addChoice("resist", "element", "S05_ChildhoodIntro");

        // S05
        scenes.get("S05_ChildhoodIntro").addChoice("play", "friends", "S07_PlayfulTraining");
        scenes.get("S05_ChildhoodIntro").addChoice("train", "teacher", "S08_Discipline");
        scenes.get("S05_ChildhoodIntro").addChoice("rebel", "rules", "S09_RebelPath");

        // S06
        scenes.get("S06_FirstMentor").addChoice("follow", "mentor", "S08_Discipline");
        scenes.get("S06_FirstMentor").addChoice("decline", "mentor", "S09_RebelPath");
        scenes.get("S06_FirstMentor").addChoice("seek", "other", "S12_MentorSwitchOption");

        // S07
        scenes.get("S07_PlayfulTraining").addChoice("help", "friend", "S10_EmpathyPath");
        scenes.get("S07_PlayfulTraining").addChoice("prank", "neighbor", "S09_RebelPath");
        scenes.get("S07_PlayfulTraining").addChoice("practice", "skill", "S08_Discipline");

        // S08
        scenes.get("S08_Discipline").addChoice("obey", "mentor", "S11_SkillPath");
        scenes.get("S08_Discipline").addChoice("question", "lesson", "S12_MentorSwitchOption");
        scenes.get("S08_Discipline").addChoice("assist", "junior", "S10_EmpathyPath");

        // S09
        scenes.get("S09_RebelPath").addChoice("run", "town", "S13_StreetEncounter");
        scenes.get("S09_RebelPath").addChoice("steal", "fruit", "S14_Scold");
        scenes.get("S09_RebelPath").addChoice("challenge", "peer", "S13_StreetEncounter");

        // S10
        scenes.get("S10_EmpathyPath").addChoice("comfort", "sick", "S11_SkillPath");
        scenes.get("S10_EmpathyPath").addChoice("share", "food", "S11_SkillPath");
        scenes.get("S10_EmpathyPath").addChoice("teach", "child", "S12_MentorSwitchOption");

        // S11
        scenes.get("S11_SkillPath").addChoice("test", "ability", "S15_VillageCrisis");
        scenes.get("S11_SkillPath").addChoice("refine", "technique", "S16_Aftermath");
        scenes.get("S11_SkillPath").addChoice("boast", "peer", "S09_RebelPath");

        // S12
        scenes.get("S12_MentorSwitchOption").addChoice("accept", "newmentor", "S21_NewMentorArc");
        scenes.get("S12_MentorSwitchOption").addChoice("combine", "teachings", "S21_NewMentorArc");
        scenes.get("S12_MentorSwitchOption").addChoice("stay", "current", "S11_SkillPath");

        // S13
        scenes.get("S13_StreetEncounter").addChoice("fight", "thugs", "S15_VillageCrisis");
        scenes.get("S13_StreetEncounter").addChoice("bargain", "merchant", "S10_EmpathyPath");
        scenes.get("S13_StreetEncounter").addChoice("flee", "alley", "S16_Aftermath");

        // S14
        scenes.get("S14_Scold").addChoice("apologize", "parent", "S10_EmpathyPath");
        scenes.get("S14_Scold").addChoice("defy", "parent", "S09_RebelPath");

        // S15
        scenes.get("S15_VillageCrisis").addChoice("defend", "village", "S17_HonorPath");
        scenes.get("S15_VillageCrisis").addChoice("evacuate", "villagers", "S18_SurvivalPath");
        scenes.get("S15_VillageCrisis").addChoice("sabotage", "enemy", "S19_CompassionPath");

        // S16
        scenes.get("S16_Aftermath").addChoice("inspect", "ruins", "S20_SelfDiscoveryStart");
        scenes.get("S16_Aftermath").addChoice("counsel", "elder", "S20_SelfDiscoveryStart");
        scenes.get("S16_Aftermath").addChoice("hunt", "loss", "S09_RebelPath");

        // S17
        scenes.get("S17_HonorPath").addChoice("recruit", "ally", "S20_SelfDiscoveryStart");
        scenes.get("S17_HonorPath").addChoice("parade", "village", "S22_TrainingArc");
        scenes.get("S17_HonorPath").addChoice("duel", "enemy", "S27_InfiltrateFortress");

        // S18
        scenes.get("S18_SurvivalPath").addChoice("hide", "forest", "S20_SelfDiscoveryStart");
        scenes.get("S18_SurvivalPath").addChoice("bargain", "stranger", "S23_SpiritEncounter");
        scenes.get("S18_SurvivalPath").addChoice("forge", "weapon", "S22_TrainingArc");

        // S19
        scenes.get("S19_CompassionPath").addChoice("heal", "wounded", "S21_NewMentorArc");
        scenes.get("S19_CompassionPath").addChoice("carry", "elder", "S21_NewMentorArc");
        scenes.get("S19_CompassionPath").addChoice("shelter", "children", "S22_TrainingArc");

        // S20
        scenes.get("S20_SelfDiscoveryStart").addChoice("walk", "forest", "S23_SpiritEncounter");
        scenes.get("S20_SelfDiscoveryStart").addChoice("meditate", "shrines", "S24_InnerVision");
        scenes.get("S20_SelfDiscoveryStart").addChoice("seek", "ruins", "S25_AncientScrolls");

        // S21
        scenes.get("S21_NewMentorArc").addChoice("learn", "technique", "S22_TrainingArc");
        scenes.get("S21_NewMentorArc").addChoice("debate", "philosophy", "S24_InnerVision");
        scenes.get("S21_NewMentorArc").addChoice("switch", "mentor", "S12_MentorSwitchOption");

        // S22
        scenes.get("S22_TrainingArc").addChoice("practice", "element", "S26_TrialOfCourage");
        scenes.get("S22_TrainingArc").addChoice("study", "scroll", "S25_AncientScrolls");
        scenes.get("S22_TrainingArc").addChoice("challenge", "trial", "S26_TrialOfCourage");

        // S23
        scenes.get("S23_SpiritEncounter").addChoice("communicate", "spirit", "S24_InnerVision");
        scenes.get("S23_SpiritEncounter").addChoice("flee", "spirit", "S20_SelfDiscoveryStart");
        scenes.get("S23_SpiritEncounter").addChoice("bargain", "spirit", "S29_PowerUnlockOrCorrupt");

        // S24
        scenes.get("S24_InnerVision").addChoice("accept", "vision", "S25_AncientScrolls");
        scenes.get("S24_InnerVision").addChoice("reject", "vision", "S22_TrainingArc");
        scenes.get("S24_InnerVision").addChoice("record", "insight", "S25_AncientScrolls");

        // S25
        scenes.get("S25_AncientScrolls").addChoice("read", "scroll", "S29_PowerUnlockOrCorrupt");
        scenes.get("S25_AncientScrolls").addChoice("burn", "scroll", "S28_CleanPath");
        scenes.get("S25_AncientScrolls").addChoice("hide", "scroll", "S22_TrainingArc");

        // S26
        scenes.get("S26_TrialOfCourage").addChoice("succeed", "trial", "S27_InfiltrateFortress");
        scenes.get("S26_TrialOfCourage").addChoice("fail", "trial", "S28_CleanPath");
        scenes.get("S26_TrialOfCourage").addChoice("cheat", "trial", "S36_LossPath");

        // S27
        scenes.get("S27_InfiltrateFortress").addChoice("sneak", "guards", "S30_FinalBattlePrep");
        scenes.get("S27_InfiltrateFortress").addChoice("fight", "patrol", "S30_FinalBattlePrep");
        scenes.get("S27_InfiltrateFortress").addChoice("parley", "captain", "S31_FinalBattleDialogue");

        // S28
        scenes.get("S28_CleanPath").addChoice("train", "steady", "S22_TrainingArc");
        scenes.get("S28_CleanPath").addChoice("mentor", "assist", "S21_NewMentorArc");
        scenes.get("S28_CleanPath").addChoice("travel", "town", "S20_SelfDiscoveryStart");

        // S29
        scenes.get("S29_PowerUnlockOrCorrupt").addChoice("embrace", "power", "S30_FinalBattlePrep");
        scenes.get("S29_PowerUnlockOrCorrupt").addChoice("restrain", "power", "S28_CleanPath");
        scenes.get("S29_PowerUnlockOrCorrupt").addChoice("test", "skill", "S26_TrialOfCourage");

        // S30
        scenes.get("S30_FinalBattlePrep").addChoice("recruit", "allies", "S31_FinalBattleDialogue");
        scenes.get("S30_FinalBattlePrep").addChoice("train", "army", "S31_FinalBattleDialogue");
        scenes.get("S30_FinalBattlePrep").addChoice("sabotage", "weapons", "S31_FinalBattleDialogue");

        // S31
        scenes.get("S31_FinalBattleDialogue").addChoice("negotiate", "enemy", "S33_EndingPeace");
        scenes.get("S31_FinalBattleDialogue").addChoice("duel", "boss", "S32_DefeatOrVictory");
        scenes.get("S31_FinalBattleDialogue").addChoice("sacrifice", "ally", "S34_SacrificeEnding");

        // S32
        scenes.get("S32_DefeatOrVictory").addChoice("win", "battle", "S35_VictoryPath");
        scenes.get("S32_DefeatOrVictory").addChoice("lose", "battle", "S36_LossPath");

        // S33
        scenes.get("S33_EndingPeace").addChoice("unify", "nations", "S35_VictoryPath");
        scenes.get("S33_EndingPeace").addChoice("rebuild", "village", "S35_VictoryPath");

        // S34
        scenes.get("S34_SacrificeEnding").addChoice("accept", "death", "S38_EpilogueReflection");
        scenes.get("S34_SacrificeEnding").addChoice("refuse", "death", "S32_DefeatOrVictory");

        // S35
        scenes.get("S35_VictoryPath").addChoice("lead", "nation", "S37_NewLeader");
        scenes.get("S35_VictoryPath").addChoice("teach", "newbender", "S37_NewLeader");
        scenes.get("S35_VictoryPath").addChoice("travel", "world", "S39_ReplayabilityStart");

        // S36
        scenes.get("S36_LossPath").addChoice("wander", "lands", "S32_DefeatOrVictory");
        scenes.get("S36_LossPath").addChoice("plot", "revenge", "S27_InfiltrateFortress");
        scenes.get("S36_LossPath").addChoice("accept", "humility", "S38_EpilogueReflection");

        // S37
        scenes.get("S37_NewLeader").addChoice("reform", "law", "S38_EpilogueReflection");
        scenes.get("S37_NewLeader").addChoice("invest", "culture", "S38_EpilogueReflection");
        scenes.get("S37_NewLeader").addChoice("found", "academy", "S39_ReplayabilityStart");

        // S38
        scenes.get("S38_EpilogueReflection").addChoice("mentor", "apprentice", "S39_ReplayabilityStart");
        scenes.get("S38_EpilogueReflection").addChoice("write", "history", "S39_ReplayabilityStart");
        scenes.get("S38_EpilogueReflection").addChoice("retire", "home", "S40_TrueEnding");

        // S39
        scenes.get("S39_ReplayabilityStart").addChoice("restart", "game", "S01_StartGame");
        scenes.get("S39_ReplayabilityStart").addChoice("change", "bender", "S02_ChooseBender");
        scenes.get("S39_ReplayabilityStart").addChoice("alternate", "ending", "S34_SacrificeEnding");

        // S40
        scenes.get("S40_TrueEnding").addChoice("ascend", "avatar", "S38_EpilogueReflection");

        return scenes;
    }
}
