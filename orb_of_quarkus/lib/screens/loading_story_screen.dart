import 'package:flutter/material.dart';
import 'dart:convert';

import 'package:animated_text_kit/animated_text_kit.dart';
import './dungeon_screen.dart';
import 'package:http/http.dart' as http;

class LoadingScreen extends StatelessWidget {
  Future<Map<String, Object>> startGameApi() async {
    const url = 'http://10.0.2.2:8080/game';
    final response = await http.post(url);
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      print("Error");
      throw Exception('Nemre');
    }
  }

  void startGame(BuildContext ctx) async {
    Map<String, Object> gameMap = await startGameApi();
    Navigator.of(ctx).pushReplacement(
      MaterialPageRoute(
        builder: (_) {
          print('game id: ' + gameMap['id'].toString());
          return DungeonScreen(
              gameId: gameMap['id'], playerId: gameMap['playerId']);
        },
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: GestureDetector(
        onTap: () => startGame(context),
        child: SafeArea(
          top: false,
          bottom: false,
          child: Container(
            color: Colors.black,
            child: Align(
              alignment: Alignment.center,
              child: SizedBox(
                width: 350.0,
                child: TyperAnimatedTextKit(
                  speed: const Duration(milliseconds: 100),
                  repeatForever: false,
                  totalRepeatCount: 1,
                  text: [
                    "When the hero is out wandering the cave",
                    "or is otherwise all alone,",
                    "a dying man bumps into him,",
                    "hands him a scribbled map,",
                    "says a few words,",
                    "and dies.",
                    "The map shows a treasure of some kind",
                    "but is otherwise scribbled",
                    "Good luck adventurer!",
                    "Press anywhere to begin!"
                  ],
                  textStyle: TextStyle(
                    fontSize: 35.0,
                    fontFamily: "Meath",
                    color: Color.fromRGBO(152, 205, 219, 1),
                  ),
                  textAlign: TextAlign.center,
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
