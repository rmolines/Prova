function getRandomArbitrary(min, max) {
    return Math.round(Math.random() * (max - min) + min);
	}

function getResult(){
	var symbols = ["Luciano", "Camila", "Hashi", "Bob Esponja", "Fabio"]

    var randInt1 = getRandomArbitrary(0,4);
    var randInt2 = getRandomArbitrary(0,4);
    var randInt3 = getRandomArbitrary(0,4);
    
    var sym1 = symbols[randInt1];
    var sym2 = symbols[randInt2];
    var sym3 = symbols[randInt3];  
	  
    var comma =',';
    
    var text = "<h2 id='sorteado'>Sorteado:" + sym1 + comma +  
	  sym2 + comma + sym3 + "</h2>";
    
    var result = (sym1 === sym2 && sym2 === sym3);
	  
	return [text, result];
}