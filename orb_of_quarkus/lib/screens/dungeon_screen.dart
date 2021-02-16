import 'package:animated_text_kit/animated_text_kit.dart';
import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

class DungeonScreen extends StatefulWidget {
  final int gameId;
  final int playerId;
  String apiPoziv;

  DungeonScreen({
    @required this.gameId,
    @required this.playerId,
  });

  @override
  _DungeonScreenState createState() => _DungeonScreenState();
}

class _DungeonScreenState extends State<DungeonScreen> {
  Future<Map<String, Object>> startGameMap(String url) async {
    final response = await http.post(url);
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Nemre');
    }
  }

  Future<Map<String, Object>> gameApi(String gameId, [String apiKind]) async {
    String url = 'http://10.0.2.2:8080/game/';
    Map<String, Object> mapa;
    if (apiKind != null) {
      if (apiKind == 'fight') {
        mapa = await startGameMap(url + gameId + '/fight');
      } else if (apiKind == 'flee') {
        mapa = await startGameMap(url + gameId + '/flee');
      } else if (apiKind.contains('heal')) {
        mapa = await startGameMap(url + apiKind);
      }
    } else {
      mapa = await startGameMap(url + gameId + '/move');
    }
    return mapa;
  }

  Future<Map<String, Object>> fight(String gameId) async {
    final response =
        await http.post('http://10.0.2.2:8080/game/' + gameId + '/fight');
    return json.decode(response.body);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: FutureBuilder(
        future: gameApi(widget.gameId.toString(), widget.apiPoziv),
        builder: (context, snapshot) {
          widget.apiPoziv = null;
          if (snapshot.connectionState == ConnectionState.done) {
            if (snapshot.data['playerHealth'] != null) {
              if (snapshot.data['playerHealth'] <= 0) {
                // DEFEAT SCREEN
                return Scaffold(
                  body: Builder(
                    builder: (context) => GestureDetector(
                      onTap: () {
                        Navigator.of(context).pop();
                      },
                      child: SafeArea(
                        top: false,
                        bottom: false,
                        child: Container(
                          decoration: BoxDecoration(
                              image: DecorationImage(
                            image: AssetImage('assets/fallen-warrior-crop.jpg'),
                            fit: BoxFit.cover,
                          )),
                          child: Container(
                            margin: EdgeInsets.fromLTRB(0, 0, 0, 70),
                            child: Align(
                              alignment: Alignment.bottomCenter,
                              child: FadeAnimatedTextKit(
                                text: [
                                  "Defeated",
                                  "Click anywhere to try again"
                                ],
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
                );
              }
            }
            if (snapshot.data['gameOver'] != null) {
              if (snapshot.data['gameOver'] == 'Defeat') {
                //DEFEAT SCREEN
                return Scaffold(
                  body: Builder(
                    builder: (context) => GestureDetector(
                      onTap: () {
                        Navigator.of(context).pop();
                      },
                      child: SafeArea(
                        top: false,
                        bottom: false,
                        child: Container(
                          decoration: BoxDecoration(
                              image: DecorationImage(
                            image: AssetImage('assets/fallen-warrior-crop.jpg'),
                            fit: BoxFit.cover,
                          )),
                          child: Container(
                            margin: EdgeInsets.fromLTRB(0, 0, 0, 70),
                            child: Align(
                              alignment: Alignment.bottomCenter,
                              child: FadeAnimatedTextKit(
                                text: [
                                  "Defeated",
                                  "Click anywhere to try again"
                                ],
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
                );
              } else {
                //VICTORY SCREEN
                return Scaffold(
                  body: Builder(
                    builder: (context) => GestureDetector(
                      onTap: () {
                        Navigator.of(context).pop();
                      },
                      child: SafeArea(
                        top: false,
                        bottom: false,
                        child: Container(
                          decoration: BoxDecoration(
                              image: DecorationImage(
                            image: AssetImage('assets/dungeon-exit.jpg'),
                            fit: BoxFit.cover,
                          )),
                          child: Container(
                            margin: EdgeInsets.fromLTRB(0, 0, 0, 70),
                            child: Align(
                              alignment: Alignment.bottomCenter,
                              child: FadeAnimatedTextKit(
                                text: [
                                  "Victory!",
                                  "Click anywhere to try again"
                                ],
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
                );
              }
            }
            if (snapshot.data['monsterId'] != null ||
                snapshot.data['name'] != null) {
              if (snapshot.data['name'] != null) {
                if (snapshot.data['health'] <= 0) {
                  //MONSTER DEFEATED SCREEN
                  return Scaffold(
                    body: Builder(
                      builder: (context) => GestureDetector(
                        onTap: () {
                          setState(() {
                            widget.apiPoziv = null;
                          });
                          ;
                        },
                        child: SafeArea(
                          top: false,
                          bottom: false,
                          child: Container(
                            decoration: BoxDecoration(
                                image: DecorationImage(
                              image: AssetImage(
                                  'assets/' + snapshot.data['monsterImage']),
                              fit: BoxFit.cover,
                            )),
                            child: Container(
                              margin: EdgeInsets.fromLTRB(0, 0, 0, 70),
                              child: Align(
                                alignment: Alignment.bottomCenter,
                                child: FadeAnimatedTextKit(
                                  text: [
                                    snapshot.data['name'] + ' defeated',
                                    "Click anywhere to continue"
                                  ],
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
                  );
                }
                //MONSTER FIGHT SCREEN
                return Scaffold(
                  body: SafeArea(
                    bottom: false,
                    top: false,
                    child: Container(
                      decoration: BoxDecoration(
                          image: DecorationImage(
                        image: AssetImage(
                            'assets/' + snapshot.data['monsterImage']),
                        fit: BoxFit.cover,
                      )),
                      child: Container(
                        alignment: Alignment.bottomCenter,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.end,
                          children: <Widget>[
                            Text(
                              snapshot.data['name'] +
                                  ': ' +
                                  snapshot.data['health'].toString(),
                              style: TextStyle(
                                  fontFamily: 'Meath',
                                  fontSize: 28,
                                  color: Colors.red),
                            ),
                            Text(
                              'Your health: ' +
                                  snapshot.data['playerHealth'].toString(),
                              style: TextStyle(
                                  fontFamily: 'Meath',
                                  fontSize: 28,
                                  color: Colors.white),
                            ),
                            Row(
                              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                              children: <Widget>[
                                RaisedButton(
                                  color: Color.fromRGBO(252, 45, 45, 1),
                                  shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(20),
                                      side: BorderSide(
                                        color: Colors.black,
                                      )),
                                  onPressed: () {
                                    setState(() {
                                      widget.apiPoziv = 'fight';
                                    });
                                  },
                                  child: Align(
                                    alignment: Alignment.center,
                                    child: Text(
                                      "Fight",
                                      style: TextStyle(
                                        fontFamily: 'Meath',
                                        fontSize: 27,
                                      ),
                                    ),
                                  ),
                                ),
                                RaisedButton(
                                  color: Color.fromRGBO(45, 252, 87, 1),
                                  shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(20),
                                      side: BorderSide(
                                        color: Colors.black,
                                      )),
                                  onPressed: () {
                                    setState(() {
                                      gameApi(widget.gameId.toString(),
                                          widget.playerId.toString() + '/heal');
                                      widget.apiPoziv = null;
                                    });
                                  },
                                  child: Align(
                                    alignment: Alignment.center,
                                    child: Text(
                                      "Heal",
                                      style: TextStyle(
                                        fontFamily: 'Meath',
                                        fontSize: 27,
                                      ),
                                    ),
                                  ),
                                ),
                              ],
                            ),
                            Row(
                              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                              children: <Widget>[
                                Visibility(
                                  child: RaisedButton(
                                    color: Color.fromRGBO(152, 205, 219, 1),
                                    shape: RoundedRectangleBorder(
                                        borderRadius: BorderRadius.circular(20),
                                        side: BorderSide(
                                          color: Colors.black,
                                        )),
                                    onPressed: () {
                                      setState(() {
                                        widget.apiPoziv = 'flee';
                                      });
                                    },
                                    child: Align(
                                      alignment: Alignment.center,
                                      child: Text(
                                        "Flee",
                                        style: TextStyle(
                                          fontFamily: 'Meath',
                                          fontSize: 27,
                                        ),
                                      ),
                                    ),
                                  ),
                                  visible: snapshot.data['name'] == 'Quarken'
                                      ? false
                                      : true,
                                ),
                                RaisedButton(
                                  color: Color.fromRGBO(0, 0, 0, 1),
                                  shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(20),
                                      side: BorderSide(
                                        color: Colors.white,
                                      )),
                                  onPressed: () {
                                    Navigator.of(context).pop();
                                  },
                                  child: Align(
                                    alignment: Alignment.center,
                                    child: Text(
                                      "Quit",
                                      style: TextStyle(
                                        fontFamily: 'Meath',
                                        color: Colors.white,
                                        fontSize: 27,
                                      ),
                                    ),
                                  ),
                                ),
                              ],
                            ),
                          ],
                        ),
                      ),
                    ),
                  ),
                );
              }
              //MONSTER AHEAD SCREEN
              return Scaffold(
                body: SafeArea(
                  bottom: false,
                  top: false,
                  child: Container(
                    decoration: BoxDecoration(
                        image: DecorationImage(
                      image:
                          AssetImage("assets/" + snapshot.data['monsterImage']),
                      fit: BoxFit.cover,
                    )),
                    child: Container(
                      alignment: Alignment.bottomCenter,
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.end,
                        children: <Widget>[
                          Text(
                            snapshot.data['monsterName'] + ' is ahead',
                            style: TextStyle(
                                fontFamily: 'Meath',
                                fontSize: 28,
                                color: Colors.white),
                          ),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: <Widget>[
                              RaisedButton(
                                color: Color.fromRGBO(252, 45, 45, 1),
                                shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(20),
                                    side: BorderSide(
                                      color: Colors.black,
                                    )),
                                onPressed: () {
                                  setState(() {
                                    widget.apiPoziv = 'fight';
                                  });
                                },
                                child: Align(
                                  alignment: Alignment.center,
                                  child: Text(
                                    "Fight",
                                    style: TextStyle(
                                      fontFamily: 'Meath',
                                      fontSize: 27,
                                    ),
                                  ),
                                ),
                              ),
                              RaisedButton(
                                color: Color.fromRGBO(45, 252, 87, 1),
                                shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(20),
                                    side: BorderSide(
                                      color: Colors.black,
                                    )),
                                onPressed: () {
                                  setState(() {
                                    gameApi(widget.gameId.toString(),
                                        widget.playerId.toString() + '/heal');
                                    gameApi(widget.gameId.toString());
                                  });
                                },
                                child: Align(
                                  alignment: Alignment.center,
                                  child: Text(
                                    "Heal",
                                    style: TextStyle(
                                      fontFamily: 'Meath',
                                      fontSize: 27,
                                    ),
                                  ),
                                ),
                              ),
                            ],
                          ),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: <Widget>[
                              Visibility(
                                child: RaisedButton(
                                  color: Color.fromRGBO(152, 205, 219, 1),
                                  shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(20),
                                      side: BorderSide(
                                        color: Colors.black,
                                      )),
                                  onPressed: () {
                                    setState(() {
                                      widget.apiPoziv = 'flee';
                                    });
                                  },
                                  child: Align(
                                    alignment: Alignment.center,
                                    child: Text(
                                      "Flee",
                                      style: TextStyle(
                                        fontFamily: 'Meath',
                                        fontSize: 27,
                                      ),
                                    ),
                                  ),
                                ),
                                visible:
                                    snapshot.data['monsterName'] == 'Quarken'
                                        ? false
                                        : true,
                              ),
                              RaisedButton(
                                color: Color.fromRGBO(0, 0, 0, 1),
                                shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(20),
                                    side: BorderSide(
                                      color: Colors.white,
                                    )),
                                onPressed: () {
                                  Navigator.of(context).pop();
                                },
                                child: Align(
                                  alignment: Alignment.center,
                                  child: Text(
                                    "Quit",
                                    style: TextStyle(
                                      fontFamily: 'Meath',
                                      color: Colors.white,
                                      fontSize: 27,
                                    ),
                                  ),
                                ),
                              ),
                            ],
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
              );
            } else if (snapshot.data['weaponId'] != null) {
              //WEAPON SCREEN
              return Scaffold(
                body: Builder(
                  builder: (context) => GestureDetector(
                    onTap: () {
                      setState(() {
                        widget.apiPoziv = null;
                      });
                      ;
                    },
                    child: SafeArea(
                      top: false,
                      bottom: false,
                      child: Container(
                        decoration: BoxDecoration(
                            image: DecorationImage(
                          image: AssetImage('assets/sword2.jpg'),
                          fit: BoxFit.cover,
                        )),
                        child: Container(
                          margin: EdgeInsets.fromLTRB(0, 0, 0, 70),
                          child: Align(
                            alignment: Alignment.bottomCenter,
                            child: FadeAnimatedTextKit(
                              text: [
                                "You've recieved " +
                                    snapshot.data['weaponName'],
                                "Click anywhere to continue"
                              ],
                              totalRepeatCount: 10000,
                              textStyle: TextStyle(
                                color: Color.fromRGBO(152, 205, 219, 1),
                                fontSize: 25,
                                fontFamily: 'Celtic',
                              ),
                            ),
                          ),
                        ),
                      ),
                    ),
                  ),
                ),
              );
            } else if (snapshot.data['healing_potion'] != null) {
              // HEALTH POTION SCREEN
              return Scaffold(
                body: Builder(
                  builder: (context) => GestureDetector(
                    onTap: () {
                      setState(() {
                        widget.apiPoziv = null;
                      });
                      ;
                    },
                    child: SafeArea(
                      top: false,
                      bottom: false,
                      child: Container(
                        decoration: BoxDecoration(
                            image: DecorationImage(
                          image: AssetImage('assets/potion.jpg'),
                          fit: BoxFit.cover,
                        )),
                        child: Container(
                          margin: EdgeInsets.fromLTRB(0, 0, 0, 70),
                          child: Align(
                            alignment: Alignment.bottomCenter,
                            child: FadeAnimatedTextKit(
                              text: [
                                "You've encountered a potion",
                                "Click anywhere to continue"
                              ],
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
              );
            }
          } else {
            return Center(child: CircularProgressIndicator());
          }
        },
      ),
    );
  }
}
