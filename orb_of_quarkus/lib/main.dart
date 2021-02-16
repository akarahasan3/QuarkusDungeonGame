import 'package:flutter/material.dart';

import 'package:animated_text_kit/animated_text_kit.dart';
import './screens/loading_story_screen.dart';

//import 'package:assets_audio_player/assets_audio_player.dart';

void main() {
  runApp(MyApp());
}

// AudioCache musicCache;
// AudioPlayer instance;

// void playLoopedMusic() async {
//     musicCache = AudioCache(prefix: "/assets/");
//     instance = await musicCache.loop("2019-05-22_-_Without_God_-_David_Fesliyan.mp3");
//     // await instance.setVolume(0.5); you can even set the volume
//   }

// void pauseMusic() {
//   if (instance != null) {
//     instance.pause();
//   }
// }

class MyApp extends StatefulWidget {
  // This widget is the root of your application.
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  //AssetsAudioPlayer _assetsAudioPlayer;

  // @override
  // void initState() {
  //   super.initState();
  //   _assetsAudioPlayer = AssetsAudioPlayer();
  //   try{
  //     _assetsAudioPlayer.open(
  //       Audio("assets/2019-05-22_-_Without_God_-_David_Fesliyan.mp3"),
  //     );
  //     _assetsAudioPlayer.playOrPause();
  //   }
  //   catch(e){
  //     print(e);
  //   }
  // }

  void startGame(BuildContext ctx) {
    Navigator.of(ctx).push(
      MaterialPageRoute(
        builder: (_) {
          return LoadingScreen();
        },
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: Scaffold(
        body: Builder(
          builder: (context) => GestureDetector(
            onTap: () => startGame(context),
            child: SafeArea(
              top: false,
              bottom: false,
              child: Container(
                decoration: BoxDecoration(
                    image: DecorationImage(
                  image: AssetImage('assets/intropic.jpg'),
                  fit: BoxFit.cover,
                )),
                child: Container(
                  margin: EdgeInsets.fromLTRB(0, 0, 0, 70),
                  child: Align(
                    alignment: Alignment.bottomCenter,
                    child: FadeAnimatedTextKit(
                      text: ["Press anywhere to start"],
                      totalRepeatCount: 10000,
                      textStyle: TextStyle(
                        color: Color.fromRGBO(152, 205, 219, 1),
                        fontSize: 30,
                        fontFamily: 'Celtic',
                      ),
                    ),
                  ),
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
