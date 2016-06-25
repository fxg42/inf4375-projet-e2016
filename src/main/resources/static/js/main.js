
var renderCitation = function (citation) {
  return '<li>'+ citation.contenu +' &mdash;'+ citation.auteur +'</li>'
}

var renderListeCitations = function (citations) {
  return '<ul>'+ citations.map(renderCitation).join('') +'</ul>'
}

var installerListeCitations = function (listeCitationsHtml) {
  document.getElementById('liste-citations').innerHTML = listeCitationsHtml
}

var fetchAll = function () {
  fetch('/citations').then(function(resp) {
    return resp.json()
  }).then(function (data) {
    installerListeCitations(renderListeCitations(data))
  })
}

document.addEventListener('DOMContentLoaded', fetchAll)
